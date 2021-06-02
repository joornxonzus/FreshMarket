package com.breezelin.freshmarket.network.asynctasks;

/**
 * Created by Breeze Lin
 * 6:55 2016/5/2
 * 1linyufeng1@gmail.com
 */

import com.breezelin.freshmarket.entities.AppVer;
import com.breezelin.freshmarket.network.FMAsyncTask;
import com.breezelin.freshmarket.network.apis.MainApi;

/**
 * 检查版本的请求
 */
public abstract class CheckVersionTask extends FMAsyncTask<String, Void, AppVer> {

	/**
	 * 检查版本的请求
	 */
	public CheckVersionTask() {
		super(MainApi.CHECK_VERSION);
	}

	@Override
	protected AppVer doInBackground(String... params) {
		return MainApi.checkVersion();
	}
}
