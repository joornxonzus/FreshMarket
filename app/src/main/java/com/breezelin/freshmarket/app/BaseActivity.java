package com.breezelin.freshmarket.app;

/**
 * Created by Breeze Lin
 * 18:09 2016/4/5
 * 1linyufeng1@gmail.com
 */

import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

import cn.jpush.android.api.JPushInterface;

/**
 * 应用内所有Activity的父类
 */
public class BaseActivity extends AppCompatActivity {

	// TODO: 2016/4/5 定制

	/**
	 * 用于打印日志的标签
	 */
	protected String TAG = BaseActivity.class.getSimpleName();

	/**
	 * 显示网络请求错误的方法
	 */
	protected void showRequestError() {
		// TODO: 2016/4/17 进行错误处理
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 极光统计
		JPushInterface.onPause(this);
		// 友盟页面统计
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 极光统计
		JPushInterface.onResume(this);
		// 友盟页面统计
		MobclickAgent.onResume(this);
	}
}
