package com.wd.tech.mvp.attn.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.IAppPresenterDataCallBack;
import com.wd.tech.mvp.attn.group.GroupView;
import com.wd.tech.mvp.attn.group.adapter.MyExpandableListViewAdapter;
import com.wd.tech.mvp.attn.group.bean.FindGroupsByUserIdBean;
import com.wd.tech.mvp.attn.group.bean.FindUserJoinedGroupBean;
import com.wd.tech.mvp.attn.group.presenter.GroupChatPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupChatFragment extends BaseFragment<GroupChatPresenter> implements IAppPresenterDataCallBack {


    @BindView(R.id.group_elistview)
    ExpandableListView groupElistview;
    Unbinder unbinder;
    private SharedPreferences sp;
    private int userId;
    private String sessionId;
    private String TAG = GroupChatFragment.class.getSimpleName();
    private MyExpandableListViewAdapter adapter;
    private FindUserJoinedGroupBean joinIdBean;
    private FindGroupsByUserIdBean userIdBean;


    public GroupChatFragment() {
    }

    @Override
    protected GroupChatPresenter getPresenter() {
        return new GroupChatPresenter(this);
    }

    @Override
    protected void initView(View view) {
        sp = getActivity().getSharedPreferences("userWife", Context.MODE_PRIVATE);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", "");


    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_group_chat;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        //我创建的群聊
        mPresenter.findGroupsByUserId(userId,sessionId);
        //查询我所有加入的群组
        mPresenter.findUserJoinedGroup(userId,sessionId);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
}

    @Override
    public void onGetDataCallBackSuc(Object bean) {
        if (bean instanceof FindGroupsByUserIdBean){
            userIdBean = (FindGroupsByUserIdBean) bean;
            //adapter.notifyDataSetChanged();

        }else if(bean instanceof FindUserJoinedGroupBean){
            joinIdBean = (FindUserJoinedGroupBean) bean;

        }
        Log.i(TAG, "onGetDataCallBackSuc: "+userIdBean.getResult().size());
        Log.i(TAG, "onGetDataCallBackSuc: "+joinIdBean.getResult().size());
        adapter = new MyExpandableListViewAdapter(getActivity(), userIdBean,joinIdBean);
        /* 1. 设置适配器*/
        groupElistview.setAdapter(adapter);
    }

    @Override
    public void onGetDataCallBackFai(String errMsg) {

    }
}
