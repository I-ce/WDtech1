package com.wd.tech.mvp.mine.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.wd.tech.base.IAppPresenterDataCallBack;
import com.wd.tech.base.IAppInteractorDataCallBack;
import com.wd.tech.base.mvpbase.BasePresenter;
import com.wd.tech.mvp.mine.bean.UserInfo;
import com.wd.tech.mvp.mine.interactor.LoginActivityInteractor;
import com.wd.tech.utils.LogUtils;
import com.wd.tech.utils.RegexUtils;
import com.wd.tech.utils.RsaCoder;
import com.wd.tech.utils.SPTool;
import com.wd.tech.utils.UIUtils;

/**
 * @author BySevenGroup_zwj
 * @create 2018/9/25 16:08
 * @Description
 */
public class LoginActivityPresenter extends BasePresenter<IAppPresenterDataCallBack<UserInfo>> {

    private LoginActivityInteractor interactor;

    public LoginActivityPresenter(IAppPresenterDataCallBack<UserInfo> view) {
        super(view);
    }

    @Override
    protected void initModel() {
        interactor = new LoginActivityInteractor();
    }

    //密码123456
    //加密后
    //String mima = "qPyPJK0NnqYKGjhX/zdcPwlcF8tMccpbjFDU/KVreivMfCGWNUEKh9pQv/zpS/dfPhnTFiKW2X2qd4dxE2iQrw10ZEZ3Ot2U49KKw7C+U0cNEn+QVeK8rFsEzIZlOm7Kh6zqNCOGj7MYZ6noF/jcjVS+LcvRrBFEtpZ+Frf27Po=";
    public void goToLogin(String phone, String pwd) {
        //Log.i("aaa","加密前pwd="+pwd);
        if(TextUtils.isEmpty(phone) && TextUtils.isEmpty(pwd)){//如果为空，返回
            view.onGetDataCallBackFai(String.valueOf("用户名和密码不能为空"));
            return;
        } else if(RegexUtils.checkPwdLength(pwd)){
            view.onGetDataCallBackFai(String.valueOf("密码不能少于6位，大于16位"));
            return;
        }else {
            try {
                interactor.goToLogin(phone, RsaCoder.encryptByPublicKey(pwd), new IAppInteractorDataCallBack<UserInfo>() {
                    @Override
                    public void getDataCallBackSuc(UserInfo bean) {
                        String pwd = bean.getResult().getPwd();
                        try {
                            String s = RsaCoder.decryptByPublicKey(pwd);
                            //LogUtils.i("aaa","解密后密码"+s);
                            String substring = s.substring(s.length() - 6, s.length());
                            Log.i("aaa","CutPwd="+substring);//打印剪切后密码
                            UserInfo.ResultBean result = bean.getResult();
                            result.setPwd(substring);
                            bean.setResult(result);//重新设置解密后密码
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        SPTool.putUserWife(bean);//存放userId和sessionId

                        SPTool.putUserAccount(bean);//自动登录,存放用户账号和密码

                        view.onGetDataCallBackSuc(bean);
                    }
                    @Override
                    public void getDataCallBackFai(String errMsg) {
                        view.onGetDataCallBackFai(errMsg);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    //自动登录
    public void autoLogin() {
        String userAccount = "";
        String userPwd = "";
        //获取用户名和密码
        userAccount = SPTool.getString(SPTool.FileName.USER_ACCOUNT, SPTool.FileKey.USER_ACCOUNT, userAccount);
        userPwd = SPTool.getString(SPTool.FileName.USER_ACCOUNT, SPTool.FileKey.USER_PWD, userPwd);
        //用户名不为空
        if (!userAccount.equals("")) {
            Log.i("aaa","自动登录，userAccount="+userAccount+"userPwd="+userPwd);
            goToLogin(userAccount, userPwd);
        }else {
            Log.i("aaa","不自动登录，userAccount="+userAccount);
        }
    }
}
