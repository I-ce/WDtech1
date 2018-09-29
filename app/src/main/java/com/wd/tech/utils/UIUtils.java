package com.wd.tech.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.app.App;
import com.wd.tech.widgets.CustomDialog;

import java.lang.reflect.Field;



/**
 * @Author: 淘跑
 * @Data: 2018/1/29 12:05
 * @Use: 和ui相关的工具类
 */
public class UIUtils {

    ////////////////////////////////Toast//////////////////////////////////////
    public static Toast mToast;
    public static void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(String msg, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), "", duration);
        }
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * 用于在线程中执行弹土司操作
     */
    /*public static void showToastSafely(final String msg) {
        UIUtils.getMainThreadHandler().post(new Runnable() {

            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
                }
                mToast.setText(msg);
                mToast.show();
            }
        });
    }*/
    ////////////////////////////////Toast//////////////////////////////////////



    /**
     * 得到屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context) {
        // 获取屏幕分辨率
        WindowManager wm = (WindowManager) (context
                .getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;
        return mScreenWidth;
    }

    /**
     * 得到屏幕高度
     *
     * @param context
     * @return
     */
    public static int getWindowHeigh(Context context) {
        // 获取屏幕分辨率
        WindowManager wm = (WindowManager) (context
                .getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int mScreenHeigh = dm.heightPixels;
        return mScreenHeigh;
    }

    /**
     * 获得状态栏/通知栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp-->px
     *
     * @param sp
     * @return
     */
    public static int sp2px(int sp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResource().getDisplayMetrics()) + 0.5f);
    }

    /**
     * 设置某个View的margin
     *
     * @param view   需要设置的view
     * @param isDp   需要设置的数值是否为DP
     * @param left   左边距
     * @param right  右边距
     * @param top    上边距
     * @param bottom 下边距
     * @return
     */
    public static ViewGroup.LayoutParams setViewMargin(View view, boolean isDp, int left, int right, int top, int bottom) {
        if (view == null) {
            return null;
        }

        int leftPx = left;
        int rightPx = right;
        int topPx = top;
        int bottomPx = bottom;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }

        //根据DP与PX转换计算值
        if (isDp) {
            leftPx = dip2px(left);
            rightPx = dip2px(right);
            topPx = dip2px(top);
            bottomPx = dip2px(bottom);
        }
        //设置margin
        marginParams.setMargins(leftPx, topPx, rightPx, bottomPx);
        view.setLayoutParams(marginParams);
        view.requestLayout();
        return marginParams;
    }

    /**
     * 得到上下文
     *
     * @return
     */
    public static Context getContext() {
        return App.getContext();
    }

    /**
     * 得到resources对象
     *
     * @return
     */
    public static Resources getResource() {
        return getContext().getResources();
    }

    /**
     * 得到string.xml中的字符串
     *
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return getResource().getString(resId);
    }

    /**
     * 得到string.xml中的字符串，带点位符
     *
     * @return
     */
    public static String getString(int id, Object... formatArgs) {
        return getResource().getString(id, formatArgs);
    }

    /**
     * 得到string.xml中和字符串数组
     *
     * @param resId
     * @return
     */
    public static String[] getStringArr(int resId) {
        return getResource().getStringArray(resId);
    }

    /**
     * 得到colors.xml中的颜色
     *
     * @param colorId
     * @return
     */
    public static int getColor(int colorId) {
        return getResource().getColor(colorId);
    }

    /**
     * 得到应用程序的包名
     *
     * @return
     */
    /*public static String getPackageName() {
        return getContext().getPackageName();
    }*/

    /**
     * 得到主线程Handler
     *
     * @return
     */
   /* public static Handler getMainThreadHandler() {
        return App.getMainHandler();
    }*/

    /**
     * 得到主线程id
     *
     * @return
     */
    /*public static long getMainThreadId() {
        return App.getMainThreadId();
    }*/

    /**
     * 安全的执行一个任务
     *
     * @param task
     */
    /*public static void postTaskSafely(Runnable task) {
        int curThreadId = android.os.Process.myTid();
        // 如果当前线程是主线程
        if (curThreadId == getMainThreadId()) {
            task.run();
        } else {
            // 如果当前线程不是主线程
            getMainThreadHandler().post(task);
        }
    }*/

    /**
     * 延迟执行任务
     *
     * @param task
     * @param delayMillis
     */
   /* public static void postTaskDelay(Runnable task, int delayMillis) {
        getMainThreadHandler().postDelayed(task, delayMillis);
    }*/

    /**
     * 移除任务
     */
    /*public static void removeTask(Runnable task) {
        getMainThreadHandler().removeCallbacks(task);
    }*/


//    public static int getDisplayWidth() {
//        if (screenWidth == 0) {
//            GetInfo(UIUtils.getContext());
//        }
//        return screenWidth;
//    }
//
//    public static int getDisplayHeight() {
//        if (screenHeight == 0) {
//            GetInfo(UIUtils.getContext());
//        }
//        return screenHeight;
//    }

//    public static void GetInfo(Context context) {
//        if (null == context) {
//            return;
//        }
//        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
//        screenWidth = dm.widthPixels;
//        screenHeight = dm.heightPixels;
//        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
//        screenMax = (screenWidth < screenHeight) ? screenHeight : screenWidth;
//        density = dm.density;
//        scaleDensity = dm.scaledDensity;
//        xdpi = dm.xdpi;
//        ydpi = dm.ydpi;
//        densityDpi = dm.densityDpi;
//        statusbarheight = getStatusBarHeight(context);
//        navbarheight = getNavBarHeight(context);
//        Log.d("UIUtils", "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density);
//    }

    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }




    ///////////////////////////////////////Dialog///////////////////////////////////////////////
    private static CustomDialog mDialogWaiting;
    //private MaterialDialog mMaterialDialog;
    /**
     * 显示等待提示框
     */
    public static Dialog showWaitingDialog(Context context, String tip) {
        hideWaitingDialog();
        View view = View.inflate(context, R.layout.dialog_waiting, null);
        ProgressBar watingProgressBar = view.findViewById(R.id.wating_progressBar);
        watingProgressBar.setIndeterminateDrawable(getContext().getResources().getDrawable(R.drawable.custom_progressbar));
        if (!TextUtils.isEmpty(tip))
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        mDialogWaiting = new CustomDialog(context, view, R.style.MyDialog);
        mDialogWaiting.show();
        //设置点击屏幕不消失 返回键也不消失
        mDialogWaiting.setCancelable(false);
        return mDialogWaiting;
    }
    /**
     * 隐藏等待提示框
     */
    public static void hideWaitingDialog() {
        if (mDialogWaiting != null) {
            mDialogWaiting.dismiss();
            mDialogWaiting = null;
        }
    }
    /**
     * 显示MaterialDialog
     */
    /*public MaterialDialog showMaterialDialog(Context context, String title, String message, String positiveText, String negativeText, View.OnClickListener positiveButtonClickListener, View.OnClickListener negativeButtonClickListener) {
        hideMaterialDialog();
        mMaterialDialog = new MaterialDialog(context);
        if (!TextUtils.isEmpty(title)) {
            mMaterialDialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            mMaterialDialog.setMessage(message);
        }
        if (!TextUtils.isEmpty(positiveText)) {
            mMaterialDialog.setPositiveButton(positiveText, positiveButtonClickListener);
        }
        if (!TextUtils.isEmpty(negativeText)) {
            mMaterialDialog.setNegativeButton(negativeText, negativeButtonClickListener);
        }
        mMaterialDialog.show();
        return mMaterialDialog;
    }*/
    /**
     * 隐藏MaterialDialog
     */
    /*public void hideMaterialDialog() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
    }*/
    ///////////////////////////////////////Dialog///////////////////////////////////////////////
}