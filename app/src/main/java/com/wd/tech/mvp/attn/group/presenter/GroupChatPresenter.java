package com.wd.tech.mvp.attn.group.presenter;

import com.wd.tech.base.IAppInteractorDataCallBack;
import com.wd.tech.base.IAppPresenterDataCallBack;
import com.wd.tech.base.mvpbase.BasePresenter;
import com.wd.tech.base.mvpbase.BaseView;
import com.wd.tech.mvp.attn.group.model.GroupModel;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @author BySevenGroup* Ice *
 * @create 2018/9/28 19:00
 * @Description
 */
public class GroupChatPresenter extends BasePresenter<IAppPresenterDataCallBack> {


    private GroupModel groupModel;

    public GroupChatPresenter(IAppPresenterDataCallBack view) {
        super(view);
    }

    @Override
    protected void initModel() {
        groupModel = new GroupModel();
    }

    public void findGroupsByUserId(int userId, String sessionId) {
        try {
            groupModel.findGroupsByUserId(new IAppInteractorDataCallBack() {
                @Override
                public void getDataCallBackSuc(Object bean) {
                    view.onGetDataCallBackSuc(bean);
                }

                @Override
                public void getDataCallBackFai(String errMsg) {
                    view.onGetDataCallBackFai(errMsg);
                }
            }, userId, sessionId);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

    }

    public void findUserJoinedGroup(int userId, String sessionId) {
            groupModel.findUserJoinedGroup(new IAppInteractorDataCallBack() {
                @Override
                public void getDataCallBackSuc(Object bean) {
                    view.onGetDataCallBackSuc(bean);
                }

                @Override
                public void getDataCallBackFai(String errMsg) {
                    view.onGetDataCallBackFai(errMsg);
                }
            }, userId, sessionId);
    }
}
