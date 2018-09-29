package com.wd.tech.ui;


import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.mvpbase.BasePresenter;
import com.wd.tech.mvp.attn.adapter.AttnFragmentChildFragmentAdapter;
import com.wd.tech.mvp.attn.adapter.AttnFragmentXRecyViewAdapter;
import com.wd.tech.mvp.attn.view.AddressBookFragment;
import com.wd.tech.mvp.attn.view.DeviceFragment;
import com.wd.tech.mvp.attn.view.FriendFragment;
import com.wd.tech.mvp.attn.view.GroupChatFragment;
import com.wd.tech.mvp.attn.view.VipCnFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AttnFragment extends BaseFragment {

    private Unbinder unbinder;

    @BindView(R.id.xRecyView)
    XRecyclerView xRView;
    private TabLayout tl;
    private ViewPager vp;
    private List <Fragment> fragmentList;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        xRView.setLayoutManager(new LinearLayoutManager(mActivity));//设置最外层XRecyView的布局

        //初始化第1层头布局
        View searchLayoutView = initSearchLayoutView();
        //初始化第2层头布局
        View twoItemLayoutView = initTwoItemLayoutView();
        //初始化第3层头布局
        View frgLayoutView = initChildFrgLayoutView();

        //添加第1层头布局(搜索框)
        xRView.addHeaderView(searchLayoutView);
        //添加第2层头布局(新朋友和创建群聊)
        xRView.addHeaderView(twoItemLayoutView);
        //添加第3层头布局(5个子Fragment)
        xRView.addHeaderView(frgLayoutView);

        xRView.setAdapter(new AttnFragmentXRecyViewAdapter());

      }
    //1
    private View initSearchLayoutView() {
        View view = View.inflate(mActivity, R.layout.frg_group_xrecyview_handler, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 20, 20, 20);
        view.setLayoutParams(params);
        return view;
    }
    //2
    private View initTwoItemLayoutView() {
        View view = View.inflate(mActivity, R.layout.frg_group_xrecyview_handler2, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,10,0,10);
        return view;
    }
    //3
    private View initChildFrgLayoutView() {
        //获取第3层布局View对象
        View childFrgLayout = View.inflate(mActivity, R.layout.frg_attn_childfrg_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0,30,0,0);
        childFrgLayout.setLayoutParams(params);
        //查找第3层布局的控件
        tl = childFrgLayout.findViewById(R.id.frg_attn_childfrg_layout_tl);
        vp = childFrgLayout.findViewById(R.id.frg_attn_childfrg_layout_vp);
        addFragment2List();//添加5个子碎片到集合
        return childFrgLayout;
    }
    private void addFragment2List() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new FriendFragment());
        fragmentList.add(new GroupChatFragment());
        fragmentList.add(new DeviceFragment());
        fragmentList.add(new AddressBookFragment());
        fragmentList.add(new VipCnFragment());
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_attn;
    }

    @Override
    protected void initListener() {
        //设置XRecyclerView的上下拉监听
        SetXRecyViewListener();
        // TODO 添加其他监听..
    }

    private void SetXRecyViewListener() {
        xRView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xRView.loadMoreComplete();
            }
        });
    }

    @Override
    protected void initData() {
        //初始化第3层头布局的的适配器
        vp.setAdapter(new AttnFragmentChildFragmentAdapter(getChildFragmentManager(),fragmentList));
    }



    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }


}
