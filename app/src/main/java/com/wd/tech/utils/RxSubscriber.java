package com.wd.tech.utils;


import android.util.Log;

import com.wd.tech.app.App;
import com.wd.tech.pojo.BaseResult;

import rx.Observer;

/**
 * @author BySevenGroup_zwj
 * @create 2018/9/25 16:20
 * @Description
 */
public abstract class RxSubscriber<T> implements Observer<T> {
    @Override
    public void onCompleted() {
        UIUtils.hideWaitingDialog();
    }
    /**
     * 请求成功回调
     * @param t 最终响应结果
     */
    public abstract void onSuccess(T t);

    @Override
    public void onNext(T t) {
        if(t instanceof BaseResult){
            BaseResult response = (BaseResult) t;
            // 判断是否请求错误，出错直接转到onError()
            if(!response.getErrorCode().equals(BaseResult.onResultOk)){
                Log.i("aaa","错误信息="+response.getErrorMsg());
                Throwable e = new Throwable(response.getErrorMsg());
                this.onError(e);
                UIUtils.showToast(response.getErrorMsg());
                return;
            }
        }
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        UIUtils.hideWaitingDialog();
        if (!NetUtils.isNetworkConnected()) {
            UIUtils.showToast("无网络，请检查网络");
            return;
        }
        if (NetUtils.isMobileNetworkConnected()) {//手机网络连接时
            UIUtils.showToast("当前网络不可用于此专线软件");
            return;
        }
        /*if (NetUtils.isWifiConnected()) {//WIFI连接时
//            UIUtils.showToast("请连接专用内网");
            UIUtils.showToast("不是内线专用网络？");
            return;
        }*/
        /*if (!NetUtils.isNetworkAvailable(App.getContext())) {
            UIUtils.showToast("你连接的网络有问题，请检查路由器");
            return;
        }*/
        //UIUtils.showToast("程序员哥哥偷懒去了，快去举报他");

        // TODO: 处理其它通用错误
        // 覆写此方法自行处理异常，通用异常使用super.onError(e)保留
        e.printStackTrace();
    }
}
