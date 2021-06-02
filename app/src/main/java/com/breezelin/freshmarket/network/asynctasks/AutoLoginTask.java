package com.breezelin.freshmarket.network.asynctasks;

/**
 * Created by Breeze Lin
 * 8:30 2016/5/2
 * 1linyufeng1@gmail.com
 */

import com.breezelin.freshmarket.network.FMAsyncTask;
import com.breezelin.freshmarket.network.apis.MainApi;

/**
 * 自动登录任务
 */
public abstract class AutoLoginTask extends FMAsyncTask<Void, Void, Object> {

	/**
	 * 自动登录任务
	 */
	public AutoLoginTask() {
		super(MainApi.AUTO_LOGIN);
	}

	@Override
	protected Object doInBackground(Void... params) {
		return MainApi.autoLogin();
	}
}
