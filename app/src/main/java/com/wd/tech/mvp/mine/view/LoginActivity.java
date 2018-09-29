package com.wd.tech.mvp.mine.view;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.IAppPresenterDataCallBack;
import com.wd.tech.mvp.mine.bean.UserInfo;
import com.wd.tech.mvp.mine.presenter.LoginActivityPresenter;
import com.wd.tech.ui.MainActivity;
import com.wd.tech.utils.RsaCoder;
import com.wd.tech.utils.SPTool;
import com.wd.tech.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity<LoginActivityPresenter> implements IAppPresenterDataCallBack<UserInfo> {

    @BindView(R.id.init_image)
    ImageView initImage;
    @BindView(R.id.act_login_pageBG)//头部没用的图片
    ImageView pageBG;
    @BindView(R.id.act_login_inputMobile)//账号输入框
    EditText inputMobile;
    @BindView(R.id.act_login_inputPwd)//密码输入框
    EditText inputPwd;
    @BindView(R.id.act_login_pwdIsShowImg)//密码的显示隐藏图标
    ImageView isShowPwd;
    @BindView(R.id.act_login_clear_pwd)//密码的显示隐藏图标
    ImageView clearPwd;
    @BindView(R.id.act_login_btn)//登录按钮
    Button loginBtn;
    @BindView(R.id.act_login_verificationCodeLogin)//验证码登录
    TextView verificationCodeLogin;
    @BindView(R.id.act_login_goToReg)//注册
    TextView goToReg;
    @BindView(R.id.act_login_wxLogin)//微信登录
    ImageView wxLogin;
    @BindView(R.id.act_login_wbLogin)//微博登录
    ImageView wbLogin;
    @BindView(R.id.act_login_qqLogin)//QQ登录
    ImageView qqLogin;
    @BindView(R.id.act_login_fbLogin)//FaceBook登录
    ImageView fbLogin;
    private Unbinder unbinder;//解绑
    private boolean pwdFlag = true;

    @Override
    protected LoginActivityPresenter getPresenter() {
        return mPresenter != null ? mPresenter : new LoginActivityPresenter(this);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initListener() {
        //点击输入框显示右侧眼睛控件
        inputPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    isShowPwd.setVisibility(View.VISIBLE);
                    clearPwd.setVisibility(View.VISIBLE);
                }else{
                    isShowPwd.setVisibility(View.GONE);
                    clearPwd.setVisibility(View.GONE);
                }
            }
        });
        pageBG.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                openActivity(MainActivity.class,null);
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        //进入程序直接 自动登录
        //autoLogin();
    }
    //自动登录
    private void autoLogin() {
        UIUtils.showWaitingDialog(this, "请骚等..");//显示Dialog
        mPresenter.autoLogin();
    }

    @Override
    protected void initView() {
        //沉浸式
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //保持竖屏
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        unbinder = ButterKnife.bind(this);

        //进入App 过渡动画
        intoAppAnimation();
    }

    public void intoAppAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setDuration(1000);
        initImage.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                initImage.setVisibility(View.GONE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @OnClick({R.id.act_login_pageBG, R.id.act_login_pwdIsShowImg, R.id.act_login_clear_pwd, R.id.act_login_btn, R.id.act_login_verificationCodeLogin, R.id.act_login_goToReg, R.id.act_login_wxLogin, R.id.act_login_wbLogin, R.id.act_login_qqLogin, R.id.act_login_fbLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_login_pageBG:
                showToast("就一个图片，点什么点！");
                break;
            case R.id.act_login_pwdIsShowImg:
                ShowAndHidePwd();//显示隐藏密码
                break;
            case R.id.act_login_clear_pwd:
                inputPwd.getText().clear();
                break;
            case R.id.act_login_btn:
                UIUtils.showWaitingDialog(this, "请骚等..");
                // 登录
                mPresenter.goToLogin(inputMobile.getText().toString().trim(), inputPwd.getText().toString().trim());
                break;
            case R.id.act_login_verificationCodeLogin:
                showToast("大爷就是不一样 ，一毛钱随便来~");
                break;
            case R.id.act_login_goToReg:
                //  TODO 注册成功，销毁3个注册页面，并传值且跳到此页面
                //去注册页面
                openActivity(RegActivity.class,null);
                break;
            /*case R.id.act_login_wxLogin:
                showToast("告诉你，我");
                break;
            case R.id.act_login_wbLogin:
                showToast("不！");
                break;
            case R.id.act_login_qqLogin:
                showToast("会！");
                break;
            case R.id.act_login_fbLogin:
                showToast("做！");
                break;*/
        }
    }
    //显示隐藏密码
    private void ShowAndHidePwd() {
        if (!pwdFlag) {//
            inputPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isShowPwd.setImageResource(R.mipmap.img_close_eye);
            pwdFlag = true;
        } else {
            inputPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isShowPwd.setImageResource(R.mipmap.img_open_eye);
            pwdFlag = false;
        }
    }

    @Override
    public void onGetDataCallBackSuc(UserInfo bean) {
        String pwd = bean.getResult().getPwd();
        try {
            String s = RsaCoder.decryptByPublicKey(pwd);
            Log.i("aaa","解密后密码"+s);
            String substring = s.substring(s.length() - 6, s.length());
             Log.i("aaa","剪切后密码+"+substring);
            UserInfo.ResultBean result = bean.getResult();
            result.setPwd(substring);
            bean.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showToast(bean.getMessage());//吐司

        SPTool.putUserWife(bean);//存放userId和sessionId

        SPTool.putUserAccount(bean);//自动登录,存放用户账号和密码

        openActivity(MainActivity.class,null);//跳转到主页面
        finish();//销毁此页面
    }

    @Override
    public void onGetDataCallBackFai(String errMsg) {
        showToast(errMsg);
    }
}
