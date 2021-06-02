package com.breezelin.freshmarket.network.asynctasks;

/**
 * Created by Breeze Lin
 * 14:29 2016/5/2
 * 1linyufeng1@gmail.com
 */

import com.breezelin.freshmarket.entities.HomeRsp;
import com.breezelin.freshmarket.network.FMAsyncTask;
import com.breezelin.freshmarket.network.apis.ContentApi;

/**
 * 获取主页内容的异步任务
 */
public abstract class HomeContentTask extends FMAsyncTask<Void, Void, HomeRsp> {

	/**
	 * 本应用异步任务基础类
	 */
	public HomeContentTask() {
		super(ContentApi.HOME_CONTENTS);
	}

	@Override
	protected HomeRsp doInBackground(Void... params) {
		return ContentApi.getHomeContents();
	}
}
