package com.wd.tech.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.wd.tech.R;
import com.wd.tech.base.mvpbase.BasePresenter;
import com.wd.tech.base.mvpbase.BaseView;
import com.wd.tech.utils.ToastUtils;

/**
 * @author BySevenGroup
 * @create 2018/8/31 15:12
 * @Description
 */

public  abstract  class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    public P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        //沉浸式
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //保持竖屏
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        /**
         *  初始化ui
         */
        initView();
        mPresenter = getPresenter();
        //初始化数据
        initData();
        //添加监听
        initListener();
    }

    public Toolbar initToolbar(int toolbarId) {
        Toolbar toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        return toolbar;
    }

    protected abstract P getPresenter();

    /**
     * 设置进入 动画
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
    }
    /**
     * 设置退出 动画
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
    }
    /**
     *  提供布局Id
     */
    protected abstract int provideLayoutId();

    /**
     * 添加监听
     */
    protected abstract void initListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化ui
     */
    protected abstract void initView(
    );

    @Override
    public void showToast(String msg) {
        ToastUtils.showShort(this,msg);
    }

    /**
     * 意图对象跳转
     * @param targetActivityClass
     * @param bundle
     */
    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
        super.onDestroy();
    }
}
