package com.wd.tech.mvp.attn.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author BySevenGroup_zwj
 * @create 2018/9/27 22:44
 * @Description
 */
public class AttnFragmentChildFragmentAdapter extends FragmentPagerAdapter {
    String[] titles = {"好友","群聊","设备","通讯录","公众号"};
    private List<Fragment> list;

    public AttnFragmentChildFragmentAdapter(FragmentManager fm, List <Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
