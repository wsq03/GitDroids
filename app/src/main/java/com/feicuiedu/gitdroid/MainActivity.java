package com.feicuiedu.gitdroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.fragment.HotRepoFragment;
import com.feicuiedu.gitdroid.login.LoginActivity;

import junit.framework.Protectable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Context mContext;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private Button mBtnLogin;
    private ImageView mIvIcon;

    private ActivityUtils mActivityUtils;
    //要切换的Fragment
    private HotRepoFragment mHotRepoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // 设置当前视图(更改了当前视图内容,将导致onContentChanged方法触发)
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        /**
         * 需要处理的视图
         * 1. toolbar
         * 2. DrawerLayout
         * 3. NavigationView
         */
        //设置Action
        setSupportActionBar(mToolbar);

        // 设置监听
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //同步当前状态
        toggle.syncState();
        //设置DrawerLayout侧滑的监听
        mDrawerLayout.addDrawerListener(toggle);


        //mNavigationView中子条目设置监听事件
        mNavigationView.setNavigationItemSelectedListener(this);

        //头布局
        mBtnLogin = ButterKnife.findById(mNavigationView.getHeaderView(0), R.id.btnLogin);
        mIvIcon = ButterKnife.findById(mNavigationView.getHeaderView(0), R.id.ivIcon);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/12/1 0001 跳转到登录界面
                //mActivityUtils.startActivity(LoginActivity.class);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        // 在主页面，默认显示的是热门仓库Fragment
//        mHotRepoFragment=new HotRepoFragment();
//        replaceFragment(mHotRepoFragment);

    }

    //创建一个Fragment
    //切换到Fragment:提供一个方法

    private void replaceFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    //主要做了我们基本登录信息的改变
    @Override
    protected void onStart() {
        super.onStart();
        // TODO: 2016/12/1 0001 展示登录用户的信息
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // TODO: 2016/12/1 0001
        //是否为选择状态
        if (item.isChecked()) {
            item.setCheckable(false);
        }
        switch (item.getItemId()) {
            //最热门
            case R.id.github_hot_repo:
                break;
            //开发者
            case R.id.github_hot_coder:
                break;
            //我的收藏
            case R.id.arsenal_my_repo:
                break;
            //每日干货
            case R.id.tips_daily:
                break;
        }
        //选择一项之后，切换Fragment并关闭抽屉
        mDrawerLayout.closeDrawer(GravityCompat.START);

        //表示已经被处理
        return true;
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
