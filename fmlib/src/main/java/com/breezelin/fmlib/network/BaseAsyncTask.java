package com.breezelin.fmlib.network;

/**
 * Created by Breeze Lin
 * 1:08 2016/5/1
 * 1linyufeng1@gmail.com
 */

import android.os.AsyncTask;

/**
 * 异步任务基类
 */
public abstract class BaseAsyncTask<Params, Progress, Result>
		extends AsyncTask<Params, Progress, Result> {

	/**
	 * 异步任务标记
	 */
	private String tag;

	/**
	 * 基础异步任务
	 *
	 * @param tag 异步任务标记
	 */
	public BaseAsyncTask(String tag) {
		this.tag = tag;
		AsyncTaskManager.getInstance().register(this);
	}

	/**
	 * @return 异步任务标记
	 */
	public String getTag() {
		return tag;
	}

	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);
		AsyncTaskManager.getInstance().unregister(this);
		if (result == null) {
			// TODO: 2016/5/2 任务结果的成功与失败应当有更详细的判断，或者在执行体中为错误统一返回null
			onError();
		} else {
			onSuccess(result);
		}
	}

	/**
	 * 任务成功的回调
	 *
	 * @param result 任务结果
	 */
	protected abstract void onSuccess(Result result);

	/**
	 * 任务失败的回调
	 */
	protected abstract void onError();

	@Override
	protected void onCancelled() {
		super.onCancelled();
		AsyncTaskManager.getInstance().unregister(this);
	}
}
