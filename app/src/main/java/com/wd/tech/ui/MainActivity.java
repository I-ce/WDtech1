package com.wd.tech.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvpbase.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {


    @BindView(R.id.act_main_beer_belly)
    FrameLayout beerBelly;
    @BindView(R.id.act_main_bottomNavigationBar)
    BottomNavigationBar btmBar;
    private Unbinder unbinder;
    private FragmentManager manager;//FragmentManager
    //3个基本碎片
    AttnFragment attnFragment;//联系人
    MessageFragment messageFragment;//群组
    InformationFragment informationFragment;//我的
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }
    private void initNavigationBar() {
        TextBadgeItem textBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorOrange)
                .setText("99+")
                .setTextColorResource(R.color.colorWhite)
                //.setBorderColorResource(R.color.colorPrimaryDark) //外边界颜
                .setHideOnSelect(true);

        btmBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.colorMainBtomNavBlue);

        btmBar.addItem(new BottomNavigationItem(R.mipmap.img_msg_nav_checked1, "消息")
                .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.img_msg_nav_normal1))
                /*.setBadgeItem(textBadgeItem)*/)

                .addItem(new BottomNavigationItem(R.mipmap.img_attn_nav_checked, "联系人")
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.img_attn_nav_normal)))

                .addItem(new BottomNavigationItem(R.mipmap.img_ifm_nav_checked2, "资讯")
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.img_ifm_nav_normal2)))

                .setFirstSelectedPosition(0)
                .initialise();
    }
    @Override
    protected void initListener() {
        btmBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                //每次点击切换对应一个新创建的事务，一个事务对应一个commit
                FragmentTransaction tr = manager.beginTransaction();
                //隐藏所有fragment
                HideAllFragment(tr);
                switch (position) {
                    case 0:
                        if (messageFragment.isHidden()) {
                            //如果隐藏，则显示，
                            //显示，则不做操作
                            show(tr, messageFragment);
                        }
                        break;
                    case 1:
                        if (attnFragment == null) {
                            addToBeerBelly(tr, attnFragment = new AttnFragment());
                        } else {
                            if (attnFragment.isHidden()) {
                                show(tr, attnFragment);
                            }
                        }
                        break;

                    case 2:
                        if (informationFragment == null) {
                            addToBeerBelly(tr, informationFragment = new InformationFragment());
                        } else {
                            if (informationFragment.isHidden()) {
                                show(tr, informationFragment);
                            }
                        }
                        break;
                }
                tr.commit();
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    @Override
    protected void initData() {
    }
    /**
     * 1、初始化碎片，并展示movie页面
     * 2、初始化rg的点击监听
     */
    private void initFragment() {
        manager = getSupportFragmentManager();
        //  准备工作完毕
        FragmentTransaction tr = manager.beginTransaction();
        //添加到头像
        addToBeerBelly(tr, messageFragment = new MessageFragment());
        tr.commit();

    }
    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        initNavigationBar();
        //初始化Fragment
        initFragment();
    }

    /**
     * @param tr        //事务
     * @param fragment  //碎片
     */
    private void addToBeerBelly(FragmentTransaction tr, Fragment fragment) {
        tr.add(R.id.act_main_beer_belly, fragment);
    }
    /**
     * @param tr             //事务
     * @param fragment  //碎片
     */
    private void show(FragmentTransaction tr, Fragment fragment) {
        tr.show(fragment);
    }

    //隐藏所有fragment
    private void HideAllFragment(FragmentTransaction tr) {
        if (attnFragment != null)
            tr.hide(attnFragment);
        if (messageFragment != null)
            tr.hide(messageFragment);
        if (informationFragment != null)
            tr.hide(informationFragment);
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
