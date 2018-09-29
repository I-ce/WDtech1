package com.wd.tech.mvp.mine.view;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvpbase.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegActivity extends BaseActivity {

    private Unbinder unbinder;
    @BindView(R.id.shiyongtiaokuan)
    TextView shiYong;
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        SpannableString spannableString = new SpannableString("注册即代表阅读并同意使用条款和隐私政策");
        spannableString.setSpan(new ForegroundColorSpan(R.color.colorDarkBlue),
                spannableString.length()-3,
                spannableString.length(),
                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        shiYong.setText(spannableString);

    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
