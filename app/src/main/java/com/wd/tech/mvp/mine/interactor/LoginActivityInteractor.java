package com.wd.tech.mvp.mine.interactor;

import android.util.Log;

import com.wd.tech.base.IAppInteractorDataCallBack;
import com.wd.tech.mvp.mine.bean.UserInfo;
import com.wd.tech.utils.RetrofitClient;
import com.wd.tech.utils.RsaCoder;
import com.wd.tech.utils.RxSubscriber;
import com.wd.tech.utils.UIUtils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author BySevenGroup_zwj
 * @create 2018/9/25 16:10
 * @Description
 */
public class LoginActivityInteractor {
    public void goToLogin(String phone, String pwd, final IAppInteractorDataCallBack listener) throws NoSuchAlgorithmException, KeyManagementException {
        //Log.i("aaa","加密后pwd="+pwd);
        RetrofitClient.getInstance().getCommonApi().goToLogin(phone,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo userInfo) {
                        listener.getDataCallBackSuc(userInfo);
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        listener.getDataCallBackFai(e.getMessage());
                    }
                });
    }
}
