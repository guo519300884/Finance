package gjw.finance.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static gjw.finance.utils.MyApplication.context;

/**
 * Created by 皇上 on 2017/3/18.
 */

public class GestureUtils {

    public static void setSetting(boolean isSetting) {
        SharedPreferences sp = context.getSharedPreferences("isSetting", Context.MODE_PRIVATE);
        sp.edit().putBoolean("isSetting", isSetting).commit();
    }

    public static boolean getIsSrtting() {
        SharedPreferences sp = context.getSharedPreferences("isSetting", Context.MODE_PRIVATE);
        return sp.getBoolean("isSetting", true);
    }

    public static void saveState(boolean isOpen) {
        SharedPreferences sp = context.getSharedPreferences("tog", Context.MODE_PRIVATE);
        sp.edit().putBoolean("isOpen", isOpen).commit();
    }

    public static boolean getState() {
        SharedPreferences sp = context.getSharedPreferences("tog", Context.MODE_PRIVATE);
        return sp.getBoolean("isOpen", false);
    }
}
