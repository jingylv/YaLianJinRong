package cn.com.afcat.www.yalianjinrong.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import cn.com.afcat.www.yalianjinrong.R;
import cn.com.afcat.www.yalianjinrong.base.BaseActivity;
import cn.com.afcat.www.yalianjinrong.home.HomeFragment;
import cn.com.afcat.www.yalianjinrong.my.MyFragment;
import cn.com.afcat.www.yalianjinrong.news.NewsFragment;
import cn.com.afcat.www.yalianjinrong.optional.OptionalFragment;
import cn.com.afcat.www.yalianjinrong.utils.ToastUtils;

/**
 * Created by jingylv on 2017/6/1.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    private List<Fragment> fragments;
    private int position = 0;
    private Fragment tempFragment;  //缓存Fragment
    private boolean isDouble = false;  //双击
    private Timer timer;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        //初始化Fragment
        initFragment();

        //初始化Fragment的监听
        initFragmentListener();
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initListener() {

    }


    private void initFragmentListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_optional:
                        position = 1;
                        break;
                    case R.id.rb_news:
                        position = 2;
                        break;
                    case R.id.rb_my:
                        position = 3;
                        break;
                }
                //根据位置切换到不同的Fragment
                Fragment currentFragment = fragments.get(position);
                switchFragmemt(currentFragment);
            }
        });

        //默认显示首页
        rgMain.check(R.id.rb_home);
    }

    private void switchFragmemt(Fragment currentFragment) {
        if (tempFragment != currentFragment) {
            //开启事务
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //如果当前页面没有被加载
            if (!currentFragment.isAdded()) {
                //如果缓存不为空,则隐藏缓存
                if (tempFragment != null) {
                    transaction.hide(tempFragment);
                }
                //添加当前页面
                transaction.add(R.id.fl_main, currentFragment);
            } else {
                //如果当前页面已经加载
                //如果缓存不为空,则隐藏缓存
                if (tempFragment != null) {
                    transaction.hide(tempFragment);
                }
                //显示当前页面
                transaction.show(currentFragment);
            }

            //提交事务
            transaction.commit();

            //把当前页面赋值给缓存
            tempFragment = currentFragment;
        }

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new OptionalFragment());
        fragments.add(new NewsFragment());
        fragments.add(new MyFragment());
    }


    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //如果不在首页,那么先跳转到首页然后再双击退出
            if (position != 0) {
                rgMain.check(R.id.rb_home);
            } else {
                if (isDouble) {
                    finish();
                    System.exit(0);
                }

                isDouble = true;
                ToastUtils.showToast(this, "再按一次将退出亚联金融");
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isDouble = false;
                    }
                }, 2000);
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
