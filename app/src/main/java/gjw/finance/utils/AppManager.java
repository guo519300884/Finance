package gjw.finance.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by 皇 上 on 2017/3/11.
 */

public class AppManager {


    /**
     * 统一应用程序中所有的Activity的栈管理（单例）
     * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
     */


    private AppManager() {
    }

    private static AppManager appManager = new AppManager();

    public static AppManager getInstance() {
        return appManager;
    }

    private Stack<Activity> stack = new Stack<>();

    public void addActivity(Activity activity) {
        //判断非空
        if (activity != null) {
            stack.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            for (int i = stack.size() - 1; i >= 0; i--) {
                Activity currentActivity = stack.get(i);
                if (currentActivity.getClass()
                        .equals(activity.getClass())) {
                    currentActivity.finish();
                    stack.remove(currentActivity);
                }
            }
        }
    }

    public void removeCurrent() {
        Activity currentActivity = stack.lastElement();
        currentActivity.finish();
        stack.remove(currentActivity);
    }

    public void removeAll() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Activity currentActivity = stack.get(i);
            if (currentActivity != null) {
                currentActivity.finish();
                stack.remove(currentActivity);
            }
        }
    }

    public void remove(Activity activity) {
        if (activity != null) {
            for (int i = stack.size() - 1; i >= 0; i--) {
                Activity currentActivity = stack.get(i);
                if (currentActivity == activity) {
//                    currentActivity.finish();
                    stack.remove(currentActivity);
                }
            }
        }
    }
}
