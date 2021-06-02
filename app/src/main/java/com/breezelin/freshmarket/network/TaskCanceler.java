package com.breezelin.freshmarket.network;

/**
 * Created by Breeze Lin
 * 8:06 2016/5/2
 * 1linyufeng1@gmail.com
 */

import com.breezelin.fmlib.network.AsyncTaskManager;
import com.breezelin.freshmarket.network.apis.ContentApi;
import com.breezelin.freshmarket.network.apis.MainApi;

/**
 * 清除页面请求的工具类
 */
public final class TaskCanceler {
	private TaskCanceler() {
		// 封闭构造
	}

	/**
	 * 清空开启页的请求
	 */
	public static void cancelSplashRequests() {
		AsyncTaskManager.getInstance().cancel(MainApi.CHECK_VERSION);
		AsyncTaskManager.getInstance().cancel(MainApi.AUTO_LOGIN);
	}

	/**
	 * 清空主页的请求
	 */
	public static void cancelHomeRequests() {
		AsyncTaskManager.getInstance().cancel(ContentApi.HOME_CONTENTS);
	}
}
