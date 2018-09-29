package com.wd.tech.service;

import com.wd.tech.mvp.attn.group.bean.FindGroupsByUserIdBean;
import com.wd.tech.mvp.attn.group.bean.FindUserJoinedGroupBean;
import com.wd.tech.mvp.mine.bean.UserInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author BySevenGroup_EveryOne
 * @create 2018/9/24 23:59
 * @Description
 */
public interface Service {
    /**
     * zwj_登录注册
     */
    //https://172.17.8.100/techApi/user/v1/login
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable <UserInfo> goToLogin(@Field("phone") String phone,
                                    @Field("pwd") String pwd);


    @GET("group/verify/v1/findGroupsByUserId")
    Observable <FindGroupsByUserIdBean>  findGroupsByUserId(@Header("userId")int userId, @Header("sessionId")String sessionId);
    @GET("group/verify/v1/findUserJoinedGroup")
    Observable<FindUserJoinedGroupBean> findUserJoinedGroup(@Header("userId")int userId, @Header("sessionId")String sessionId);

}
