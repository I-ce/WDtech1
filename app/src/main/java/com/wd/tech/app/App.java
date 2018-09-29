package com.wd.tech.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.wd.tech.utils.SPTool;

/**
 * @author BySevenGroup_zwj
 * @create 2018/9/24 11:28
 * @Description
 */
public class App extends Application {

    public static Gson gson;
    private static Context mContext;//上下文

    @Override
    public void onCreate() {
        super.onCreate();
        init_By_zwj();
    }
    //一些杂的初始化
    private void init_By_zwj() {
        gson = new Gson();//Gson
        Fresco.initialize(this);//Fresco
        SPTool.init(this);//SharedPreferences
        mContext = getApplicationContext();
    }
    public static Context getContext() {
        return mContext;
    }
}
