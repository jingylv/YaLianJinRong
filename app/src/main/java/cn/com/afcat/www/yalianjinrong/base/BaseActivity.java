package cn.com.afcat.www.yalianjinrong.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.afcat.www.yalianjinrong.utils.AppManager;

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initTitle();
        initData();
        initListener();


    }

    public abstract int getLayoutId();

    public abstract void initData();

    public abstract void initTitle();

    public abstract void initListener();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        AppManager.getInstance().removeActivity(this);
    }

}
