package com.wd.tech.utils;

/**
 * @author SevenGroup-zwj
 * @create 2018/8/10 15:02
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.wd.tech.mvp.mine.bean.UserInfo;

/**
 *
 * 注意要点：1、需要在application中调用init()，
 *           2、需要在清单文件中声名
 *           3、需要在每个get和put方法中先调用throwInit()
 *           4、创建一个Constants类，里面定义一些静态final常量，存放文件名或者key值(脑袋智障了忘掉咋整?)
 * @author admin
 * @version 1.0
 * @create 2018/6/28
 */
public class SPTool {

    public interface FileName{
        String USER_INFO = "userInfo";//存放LoginBean和WxUserInfo

        String USER_WIFE = "userWife";//存放userId和sessionId

        String USER_ACCOUNT = "Account";//存放用户账号密码

        String WX_CONFIG = "WXConfig";//4、存放微信登录
    }
    public interface FileKey{
        /**
         *  1、存放LoginBean和WxUserInfo
         */
        String USER_INFO = "userInfoKey";

        /**
         *  2、用户账号密码
         */
        String USER_ACCOUNT =  "userAccount";
        String USER_PWD = "userPwd";

        /**
         * 3、存放用户id和SessionId的文件
         */
        String USER_ID = "userId";
        String SESSION_ID = "sessionId";

        /**
         *  4、 App.buGaoSuNi.put("isWXLogined", "yeah");
         */
        String IS_WX_LOGINED = "isWXLogined";
    }

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    private static void throwInit() {
        if (mContext == null) {
            throw new NullPointerException("在使用该方法前，需要init()方法，推荐init()放在application里");
        }
    }
    //清空sp内容
    public static void clear(String fileName) {
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    public static boolean putBoolean(String fileName,String key,boolean value) {
        throwInit();
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.edit().putBoolean(key,value).commit();
    }
    public static boolean getBoolean(String fileName,String key,boolean defaultValue) {
        throwInit();
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }
    public static boolean putString(String fileName,String key,String value) {
        throwInit();
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.edit().putString(key,value).commit();
    }
    public static String getString(String fileName,String key,String defaultValue) {
        throwInit();
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    /**
     *  存放
     *  userId和sessionId
     */
    public static void putUserWife(UserInfo userInfo) {
        throwInit();
        SharedPreferences sp = mContext.getSharedPreferences(FileName.USER_WIFE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(FileKey.USER_ID,userInfo.getResult().getUserId());
        edit.putString(FileKey.SESSION_ID, userInfo.getResult().getSessionId());
        edit.apply();
    }

    /**
     *  存放userInfo
     */
    public static void putUserAccount(UserInfo userInfo) {
        throwInit();
        SharedPreferences sp = mContext.getSharedPreferences(FileName.USER_ACCOUNT, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(FileKey.USER_ACCOUNT,userInfo.getResult().getPhone());
        edit.putString(FileKey.USER_PWD, userInfo.getResult().getPwd());
        edit.apply();
    }
}

