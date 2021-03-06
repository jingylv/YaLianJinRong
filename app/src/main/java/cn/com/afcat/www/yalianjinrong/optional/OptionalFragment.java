package cn.com.afcat.www.yalianjinrong.optional;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import cn.com.afcat.www.yalianjinrong.base.BaseFragment;

/**
 * Created by jingylv on 2017/5/31.
 * 自选
 */

public class OptionalFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        Log.e("TAG", "自选视图初始化了");
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        return textView;
    }


    @Override
    public void initData() {
        Log.e("TAG", "自选数据初始化了");
        super.initData();
        textView.setText("自选");
    }
}
