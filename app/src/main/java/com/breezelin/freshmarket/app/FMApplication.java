package com.breezelin.freshmarket.app;

/**
 * Created by Breeze Lin
 * 20:07 2016/4/5
 * 1linyufeng1@gmail.com
 */

import android.app.Application;
import android.widget.Toast;

import com.breezelin.fmlib.network.AsyncTaskManager;
import com.breezelin.fmlib.utils.FMLog;
import com.breezelin.freshmarket.BuildConfig;
import com.breezelin.freshmarket.utils.LocalDataUtil;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义的application
 */
public class FMApplication extends Application {

	/**
	 * 用于日志打印的标签
	 */
	private static final String TAG = "FMApplication";

	/**
	 * 如果程序在运行中，则可调用application用作context
	 */
	public static FMApplication application;
	/**
	 * 登录状态标记
	 */
	public static boolean isLogin;

	// TODO: 2016/4/5 进行定制

	@Override
	public void onCreate() {
		super.onCreate();

		// 进行一些内容的初始化

		FMLog.d(TAG, "init push");
		// 推送功能初始化
		JPushInterface.init(this);
		FMLog.d(TAG, "init push debug mode");
		// TODO: 2016/4/11 开启了JPush的日志打印，应当在正式包中关闭
		JPushInterface.setDebugMode(BuildConfig.DEBUG);
		// 本地内容存储初始化
		LocalDataUtil.createInstance(this);
		// 异步任务管理器初始化
		AsyncTaskManager.createInstance();
		// 将自身赋给静态变量
		application = this;
	}

	/**
	 * 使用application弹出toast提示
	 *
	 * @param content 提示内容
	 */
	public static void showToast(String content) {
		if (application != null) {
			Toast.makeText(application, content, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 使用application弹出toast提示
	 *
	 * @param contentRes 提示内容
	 */
	public static void showToast(int contentRes) {
		if (application != null) {
			Toast.makeText(application, contentRes, Toast.LENGTH_SHORT).show();
		}
	}
}
