package gjw.finance.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by 皇 上 on 2017/3/11.
 */

public class UIUtils {

    public static Context getContext() {
        return MyApplication.getContext();
    }

    private static View getView(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    private static int getColor(int color) {
        return getContext().getResources().getColor(color);
    }

    private static String[] getString(int StringId) {
        return getContext().getResources().getStringArray(StringId);
    }

    //与屏幕分辨率相关的
    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }

    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

}
