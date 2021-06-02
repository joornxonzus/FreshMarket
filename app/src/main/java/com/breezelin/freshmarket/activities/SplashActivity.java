package com.breezelin.freshmarket.activities;

/**
 * Created by Breeze Lin
 * 18:11 2016/04/05
 * 1linyufeng1@gmail.com
 */

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.breezelin.fmlib.utils.TextUtils;
import com.breezelin.freshmarket.R;
import com.breezelin.freshmarket.app.BaseActivity;
import com.breezelin.freshmarket.app.FMApplication;
import com.breezelin.freshmarket.entities.AppVer;
import com.breezelin.freshmarket.network.TaskCanceler;
import com.breezelin.freshmarket.network.asynctasks.AutoLoginTask;
import com.breezelin.freshmarket.network.asynctasks.CheckVersionTask;
import com.breezelin.freshmarket.utils.LocalDataUtil;

/**
 * 主页
 */
public class SplashActivity extends BaseActivity {

	/**
	 * 主页标题，用于延时操作
	 */
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		title = (TextView) findViewById(R.id.title);

		new CheckVersionTask() {
			@Override
			protected void onSuccess(AppVer appVer) {
				// 请求成功，读秒打开主页或欢迎页
				redirect();
			}

			@Override
			protected void onError() {
				super.onError();
				// 如果网络错误，则退出程序
				finish();
			}
		}.execute();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 清理页面请求
		TaskCanceler.cancelSplashRequests();
	}

	/**
	 * 重新定向要去的页面，并进行必要的操作
	 */
	private void redirect() {
		// 检查是否第一次打开
		String firstOpen = LocalDataUtil.getInstance().get(LocalDataUtil.KEY_FIRST_OPEN);
		if (firstOpen.equals("opened")) {

			// 如果本地存在用户资料，进行一次登录请求，然后打开主页
			String userName = LocalDataUtil.getInstance().get(LocalDataUtil.KEY_USERNAME);
			String password = LocalDataUtil.getInstance().get(LocalDataUtil.KEY_PASSWORD);
			if (!(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password))) {
				new AutoLoginTask() {
					@Override
					protected void onSuccess(Object o) {
						// 登录成功，标记为已登录
						FMApplication.isLogin = true;
						// 打开主页
						openMain();
					}

					@Override
					protected void onError() {
						super.onError();
						// 登录失败，标记为未登录
						FMApplication.isLogin = false;
						// 打开主页
						openMain();
					}
				}.execute();
			} else {
				// 没有本地资料，标记为未登录
				FMApplication.isLogin = false;
				// 直接打开主页
				openMain();
			}
		} else {
			// 标记第一次打开
			LocalDataUtil.getInstance().put(LocalDataUtil.KEY_FIRST_OPEN, "opened");
			// 没有登录，标记为未登录
			FMApplication.isLogin = false;
			// 打开引导页和主页
			openGuide();
		}
	}

	/**
	 * 打开主页
	 */
	private void openMain() {
		title.postDelayed(
				new Runnable() {
					@Override
					public void run() {
						// 打开主页
						startActivity(new Intent(SplashActivity.this, MainActivity.class));
						finish();
					}
				}, 2000
		);
	}

	/**
	 * 打开引导页
	 */
	private void openGuide() {
		title.postDelayed(new Runnable() {
			@Override
			public void run() {
				// 打开主页
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				// TODO: 2016/5/2 打开引导页
			}
		}, 2000);
	}
}
