package com.breezelin.freshmarket.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.breezelin.freshmarket.R;
import com.breezelin.freshmarket.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

	/**
	 * 标题栏
	 */
	private Toolbar toolbar;
	/**
	 * 抽屉
	 */
	private DrawerLayout drawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 绑定视图
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

		// 设置标题栏
		setSupportActionBar(toolbar);
		// 返回键可用
		//noinspection ConstantConditions
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// 抽屉的监听
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
				R.string.app_name, R.string.app_name) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		};
		drawerToggle.syncState();
		drawerLayout.addDrawerListener(drawerToggle);

		// TODO: 2016/5/2 抽屉内的内容

		// 先打开主页
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.frame_content, new HomeFragment(), HomeFragment.TAG);
		transaction.commit();
	}
}
