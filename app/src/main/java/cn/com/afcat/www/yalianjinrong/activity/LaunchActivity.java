package cn.com.afcat.www.yalianjinrong.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.MotionEvent;

import cn.com.afcat.www.yalianjinrong.R;
import cn.com.afcat.www.yalianjinrong.base.BaseActivity;


public class LaunchActivity extends BaseActivity {
    private Handler handler = new Handler();
    private boolean override = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    public void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, 3000);
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initListener() {

    }

    private void startMainActivity() {
        if (!override) {
            override = true;

            //跳转到主界面
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            //结束当前界面
            finish();
        }

    }

    //触摸事件 : 如果不想等待3秒,需要立刻进入主界面,可以设置触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startMainActivity();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
