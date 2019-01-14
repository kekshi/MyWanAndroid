package com.kekshi.library.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import com.kekshi.library.utils.PreferencesUtil;


/**
 * Created by Horrarndoo on 2017/9/1.
 * <p>
 * 全局Application
 */

public class GlobalApplication extends Application {
    protected static Context context;
    protected static Handler handler;
    protected static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
        PreferencesUtil.Companion.get(this);

    }

    /**
     * 获取上下文对象
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }
}
