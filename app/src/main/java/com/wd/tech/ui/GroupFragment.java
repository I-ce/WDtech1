package com.wd.tech.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.mvpbase.BasePresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GroupFragment extends BaseFragment {

    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.start)
    Button start;
    Unbinder unbinder;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_group;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
