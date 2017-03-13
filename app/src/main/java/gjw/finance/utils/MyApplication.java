package gjw.finance.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;

/**
 * Created by 皇 上 on 2017/3/10.
 */

public class MyApplication extends Application {

    public static Context context;//
    public static Handler handler;//handler
    public static Thread mainThread;//
    public static int threadId;//id

    /**
     * 1、onCreate（） 在创建应用程序时创建
     * <p>
     * 2、onTerminate（） 在模拟环境下执行。
     * 当终止应用程序对象时调用，不保证一定被调用，
     * 当程序是被内核终止以便为其他应用程序释放资源，
     * 那么将不会提醒，并且不调用应用程序的对象的onTerminate方法而直接终止进程。
     * <p>
     * 3、onLowMemory（） 低内存时执行。
     * 好的应用程序一般会在这个方法里面释放一些不必要的资源来应付当后台程序已经终止，
     * 前台应用程序内存还不够时的情况。
     * <p>
     * 4、onConfigurationChanged（Configuration newConfig） 配置改变时触发这个方法。
     * <p>
     * 5、onTrimMemory（int level）程序在进行内存清理时执行​
     */


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        //初始化异常捕获类
        //实际此功能到上线前开启
//        CrashHandler.getInstence().init();
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }


    public static Thread getMainThread() {
        return mainThread;
    }


    public static int getThreadId() {
        return threadId;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
