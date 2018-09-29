package com.wd.tech.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.mvpbase.BasePresenter;

public class InformationFragment extends BaseFragment {


    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
