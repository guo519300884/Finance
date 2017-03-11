package gjw.finance;

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
    public static int mainThreadId;//id

    public MyApplication() {
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyApplication.context = context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static void setHandler(Handler handler) {
        MyApplication.handler = handler;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static void setMainThread(Thread mainThread) {
        MyApplication.mainThread = mainThread;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static void setMainThreadId(int mainThreadId) {
        MyApplication.mainThreadId = mainThreadId;
    }


    @Override
    public void onCreate() {
        super.onCreate();
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
