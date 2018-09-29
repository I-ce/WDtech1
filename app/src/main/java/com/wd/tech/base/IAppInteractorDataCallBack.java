package com.wd.tech.base;

/**
 * @author BySevenGroup_zwj
 * @create 2018/9/2 21:48
 * @Description
 */
public interface IAppInteractorDataCallBack<T> {

    void getDataCallBackSuc(T bean);

    void getDataCallBackFai(String errMsg);
}
