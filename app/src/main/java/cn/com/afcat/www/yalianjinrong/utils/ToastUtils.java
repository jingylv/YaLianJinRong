package cn.com.afcat.www.yalianjinrong.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jingylv on 2017/5/16.
 */

public class ToastUtils {
    //Toast对象
    private static Toast toast = null;

    public static void showToast(Context mContext, String msg) {
        if (toast == null) {
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        } else {
//            toast.cancel();
//            toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
            toast.setText(msg);
        }
        toast.show();
    }
}
