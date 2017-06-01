package cn.com.afcat.www.yalianjinrong.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jingylv on 2017/5/31.
 */

public abstract class BaseFragment extends Fragment {
    //上下文
    public Context mContext;

    //fragment被系统创建的时候调用
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    //系统调用该方法实例化视图的时候
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    //由子类实现该方法,显示不同的子类视图
    public abstract View initView();


    //activity 加载数据的时候调用
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要:
     * 1.控件上绑定数据的时候,重写该方法
     * 2.联网请求,得到数据绑定到视图上的时候重写该方法
     */
    public void initData() {

    }
}
