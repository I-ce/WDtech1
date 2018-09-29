package com.wd.tech.mvp.attn.group.model;

import com.wd.tech.base.IAppInteractorDataCallBack;
import com.wd.tech.mvp.attn.group.bean.FindGroupsByUserIdBean;
import com.wd.tech.mvp.attn.group.bean.FindUserJoinedGroupBean;
import com.wd.tech.utils.RetrofitClient;
import com.wd.tech.utils.RxSubscriber;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author BySevenGroup* Ice *
 * @create 2018/9/28 19:11
 * @Description
 */
public class GroupModel {

    public void findGroupsByUserId(final IAppInteractorDataCallBack callBack, int userId, String sessionId) throws NoSuchAlgorithmException, KeyManagementException {

        Observable<FindGroupsByUserIdBean> groupsByUserId = RetrofitClient.getInstance().getCommonApi().findGroupsByUserId(userId, sessionId);
        groupsByUserId.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<FindGroupsByUserIdBean>() {
                    @Override
                    public void onSuccess(FindGroupsByUserIdBean findGroupsByUserIdBean) {
                        callBack.getDataCallBackSuc(findGroupsByUserIdBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        callBack.getDataCallBackFai(e.getMessage());
                    }
                });
    }

    public void findUserJoinedGroup(final IAppInteractorDataCallBack callBack, int userId, String sessionId) {

        try {
            Observable<FindUserJoinedGroupBean> groupsByUserId = RetrofitClient.getInstance().getCommonApi().findUserJoinedGroup(userId, sessionId);
            groupsByUserId.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RxSubscriber<FindUserJoinedGroupBean>() {
                        @Override
                        public void onSuccess(FindUserJoinedGroupBean findUserJoinedGroupBean) {
                            callBack.getDataCallBackSuc(findUserJoinedGroupBean);
                        }
                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            callBack.getDataCallBackFai(e.getMessage());
                        }
                    });
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
