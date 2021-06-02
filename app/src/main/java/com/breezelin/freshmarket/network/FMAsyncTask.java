package com.breezelin.freshmarket.network;

/**
 * Created by Breeze Lin
 * 6:55 2016/5/2
 * 1linyufeng1@gmail.com
 */

import com.breezelin.fmlib.network.BaseAsyncTask;
import com.breezelin.freshmarket.R;
import com.breezelin.freshmarket.app.FMApplication;

/**
 * 本应用的异步任务基类
 */
public abstract class FMAsyncTask<Params, Progress, Result>
		extends BaseAsyncTask<Params, Progress, Result> {

	/**
	 * 本应用异步任务基础类
	 *
	 * @param tag 异步任务标记
	 */
	public FMAsyncTask(String tag) {
		super(tag);
	}

	/**
	 * 如果网络请求出现错误，则直接显示toast
	 */
	@Override
	protected void onError() {
		FMApplication.showToast(R.string.offline_or_svr_error);
	}
}
