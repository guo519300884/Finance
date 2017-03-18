package gjw.finance.utils;

import android.content.SharedPreferences;
import android.os.Environment;

import java.io.File;

import gjw.finance.bean.DataBean;
import gjw.finance.bean.UserInfo;

import static android.content.Context.MODE_PRIVATE;
import static gjw.finance.utils.MyApplication.context;

/**
 * Created by 皇上 on 2017/3/16.
 */

public class CacheUtils {

    private static File filesDir;


    //获取用户信息
    public static UserInfo getUser() {
        SharedPreferences sp = context.getSharedPreferences("user_info", MODE_PRIVATE);
        String imageurl = sp.getString("imageurl", "");
        String iscredit = sp.getString("iscredit", "");
        String name = sp.getString("name", "");
        String phone = sp.getString("phone", "");

        UserInfo userInfo = new UserInfo();
        DataBean dataBean = new DataBean();
        dataBean.setImageurl(imageurl);
        dataBean.setIscredit(iscredit);
        dataBean.setName(name);
        dataBean.setPhone(phone);
        userInfo.setData(dataBean);
        return userInfo;
    }

    //保存用户信息
    public static void setUser(UserInfo userInfo) {
        SharedPreferences sp = context.getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("imageurl", userInfo.getData().getImageurl());
        edit.putString("iscredit", userInfo.getData().getIscredit());
        edit.putString("name", userInfo.getData().getName());
        edit.putString("phone", userInfo.getData().getPhone());
        edit.commit();
    }

    public static void saveImage(Boolean isUpdate) {
        SharedPreferences sp = context.getSharedPreferences("image", MODE_PRIVATE);
        sp.edit().putBoolean("update", isUpdate).commit();
    }

    public static Boolean isUpdate() {
        SharedPreferences sp = context.getSharedPreferences("image", MODE_PRIVATE);
        return sp.getBoolean("update", false);
    }

    //清除缓存
    public static void cleatSP() {
        SharedPreferences user = context.getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences image = context.getSharedPreferences("image", MODE_PRIVATE);
        //清除保存的数据
        user.edit().clear().commit();
        image.edit().clear().commit();
    }

    //清除存储数据的目录
    public static void clearFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filesDir = context.getExternalFilesDir("");
        } else {
            filesDir = context.getFilesDir();
        }
        File path = new File(filesDir, "123.png");
        if (path.exists()) {
            //删除目录
            path.delete();
        }
    }
}
