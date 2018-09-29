package com.wd.tech.mvp.attn.group.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.mvp.attn.group.bean.FindGroupsByUserIdBean;
import com.wd.tech.mvp.attn.group.bean.FindUserJoinedGroupBean;

import org.w3c.dom.Text;

/**
 * @author BySevenGroup* Ice *
 * @create 2018/9/28 19:54
 * @Description
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private FindGroupsByUserIdBean list;
    private FindUserJoinedGroupBean list2;
    private String[] group = new String[]{"我创建的群聊", "我加入的群聊"};

    public MyExpandableListViewAdapter(Context context, FindGroupsByUserIdBean list, FindUserJoinedGroupBean joinIdBean) {
        this.context = context;
        this.list = list;
        this.list2 = joinIdBean;
    }

    @Override
    public int getGroupCount() {
        return group.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition==0){
            return list.getResult().size();
        }else if(groupPosition==1){
            return list2.getResult().size();
        }
       return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.getResult().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.group_elist_item_elv_group, null);
            groupHolder = new GroupHolder();
            groupHolder.iv_group_icon = (ImageView) convertView.findViewById(R.id.iv_group_icon);
            groupHolder.textView1 = (TextView) convertView.findViewById(R.id.tv_group_name);
            groupHolder.textView2 = (TextView) convertView.findViewById(R.id.tv_group_number);
            convertView.setTag(groupHolder);
        }
        groupHolder = (GroupHolder) convertView.getTag();
        groupHolder.textView1.setText(group[groupPosition]);
        if (groupPosition==0){
            groupHolder.textView2.setText(list.getResult().size()+"");
        }else{
            groupHolder.textView2.setText(list.getResult().size()+"");
        }

        /*isExpanded 子列表是否展开*/
        if (isExpanded) {
            groupHolder.iv_group_icon.setImageResource(R.drawable.arrow_down);
        } else {
            groupHolder.iv_group_icon.setImageResource(R.drawable.arrow_right);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (groupPosition == 0) {
            ChildHolder childHolder = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.group_elist_item_elv_child, null);
                childHolder = new ChildHolder();
                childHolder.iv_child_icon = (SimpleDraweeView) convertView.findViewById(R.id.iv_child_icon);
                childHolder.tv_child_info = (TextView) convertView.findViewById(R.id.tv_child_info);
                childHolder.tv_child_name = (TextView) convertView.findViewById(R.id.tv_child_name);
                convertView.setTag(childHolder);
            }
            childHolder = (ChildHolder) convertView.getTag();
            childHolder.tv_child_name.setText(list.getResult().get(childPosition).getGroupName());
            childHolder.iv_child_icon.setImageURI("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538148224855&di=3ad83d8f8f60d8156c71d6bb17c88e90&imgtype=0&src=http%3A%2F%2Fwww.haoqilu.cn%2Fuploadfile%2F2016%2F0914%2F20160914044519895.png");
            return convertView;
        } else if (groupPosition == 1) {
            ChildHolder childHolder = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.group_elist_item_elv_child, null);
                childHolder = new ChildHolder();
                childHolder.iv_child_icon = (SimpleDraweeView) convertView.findViewById(R.id.iv_child_icon);
                childHolder.tv_child_info = (TextView) convertView.findViewById(R.id.tv_child_info);
                childHolder.tv_child_name = (TextView) convertView.findViewById(R.id.tv_child_name);
                convertView.setTag(childHolder);
            }
            childHolder = (ChildHolder) convertView.getTag();
            childHolder.tv_child_name.setText(list2.getResult().get(childPosition).getGroupName());
            childHolder.iv_child_icon.setImageURI("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538148224855&di=3ad83d8f8f60d8156c71d6bb17c88e90&imgtype=0&src=http%3A%2F%2Fwww.haoqilu.cn%2Fuploadfile%2F2016%2F0914%2F20160914044519895.png");
            return convertView;
        }
        return null;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        private ImageView iv_group_icon;
        private TextView textView1;
        private TextView textView2;
    }

    class ChildHolder {
        private SimpleDraweeView iv_child_icon;
        private TextView tv_child_info;
        private TextView tv_child_name;
        private TextView tv_child_network;
    }
}
