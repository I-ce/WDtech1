package com.wd.tech.base;


import com.wd.tech.base.mvpbase.BaseView;

/**
 * @author BySevenGroup_zwj
 * @create 2018/9/8 17:38
 * @Description
 */
public interface IAppPresenterDataCallBack<T> extends BaseView {

    void onGetDataCallBackSuc(T bean);
    void onGetDataCallBackFai(String errMsg);
}
