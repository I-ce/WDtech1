package com.wd.tech.utils;



import android.util.Log;


import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by xyj on 2018/9/1.
 * //加密依赖
 *     implementation 'commons-codec:commons-codec:1.11'
 */
public class RsaCoder {

    //非对称密钥算法
    public static final String KEY_ALGORITHM="RSA";

    //公钥
    private static PublicKey publicKey;

    //初始化
    static {
        publicKey = getPublicKey(KEY_ALGORITHM);
    }


    /**
     *  获取公钥
     * @param algorithm
     * @return
     */
    private static PublicKey getPublicKey(String algorithm) {
        try {
            String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuQd3gESx7VdIyRYUWjmjg61VIgUK6F45hClmqUMZ7xNiT5rlLM6e78osMvBF/yP7cVm7pK+NMCDWoVS1/AETpxJYqUlIC77ZAU8/MnP96IupnJL87vqhPcpdv7+VqLM38ls++yuD/F/HSoOQTv/leJh+dgE/4EYAJjOWFAbYfXwIDAQAB";
            Base64 b64 = new Base64();
            byte [] decoded = b64.decode(key);
            KeyFactory keyFactory=KeyFactory.getInstance(algorithm);
            X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(decoded);
            return keyFactory.generatePublic(x509KeySpec);
        }catch (Exception e) {
            return null;
        }
    }

    /**
     *  公钥解密
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String data) throws Exception{
        try {

            if(null == data || "".equals(data))
            {
                return null;
            }

            byte[] key = publicKey.getEncoded();
//            byte[] datas = Base64.decodeBase64(data);
            byte[] datas = Base64.decode(data);
            //实例化密钥工厂
            KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
            //初始化公钥
            //密钥材料转换
            X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(key);
            //产生公钥
            PublicKey pubKey=keyFactory.generatePublic(x509KeySpec);
            //数据解密
            String algorithm = keyFactory.getAlgorithm();
            System.out.println(algorithm);

            Cipher cipher=Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            String s = new String(cipher.doFinal(datas));
            System.out.println(s);

            return s;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     *  公钥加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data) throws Exception{
        try {

            if(null == data || "".equals(data))
            {
                return null;
            }

            byte[] key = publicKey.getEncoded();
            byte[] datas = data.getBytes();
            //实例化密钥工厂
            KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
            //初始化公钥
            //密钥材料转换
            X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(key);
            //产生公钥
            PublicKey pubKey=keyFactory.generatePublic(x509KeySpec);

            //数据加密
            //Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
            System.out.println("RSA/ECB/PKCS1Padding="+keyFactory.getAlgorithm());
            Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");

            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return Base64.encode(cipher.doFinal(datas));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //公钥加密数据
        String jia = encryptByPublicKey("123456");
        System.out.println("加密后"+jia);
        /*String jie = decryptByPublicKey(mi);
        System.out.println("解密后"+jie);*/
        //公钥解密数据 解密不行 只能解密服务器返回的数据 不能解密自己加密的数据
//        System.out.println(decryptByPublicKey("KN/Nwy4MgL2T0qDrzxtCdvEjJX/pBFTIh+j1csaOYdvzcdt4KKAHMW0R+I+ymjkmjqk4UD1MShKlnNx5aNB6U5VXCDc3YWFkJl2RLehTfCXQgETy3m2stwDqyNZ/vp3XgSbqjG5C7bOumi87h5ud10Cy+mEIjq0fBa1DX/XB6P8=\n"));

    }
}
