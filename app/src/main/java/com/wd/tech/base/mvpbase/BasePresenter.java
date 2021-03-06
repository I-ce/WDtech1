package com.wd.tech.base.mvpbase;

/**
 * @author BySevenGroup
 * @create 2018/8/31 15:12
 * @Description
 */
public abstract class BasePresenter<V extends BaseView> {

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        initModel();
    }


    protected abstract void initModel();

    public void onDestory() {
        view = null;
    }
}
