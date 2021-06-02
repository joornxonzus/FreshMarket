package com.breezelin.fmlib.network;

/**
 * Created by Breeze Lin
 * 11:34 2016/5/1
 * 1linyufeng1@gmail.com
 */

import java.util.ArrayList;

/**
 * 异步任务管理类<br/>
 * 主要用于一部任务的取消
 */
public class AsyncTaskManager {

	/**
	 * 异步任务管理器实例
	 */
	private static AsyncTaskManager instance;

	/**
	 * 创建异步任务管理器实例
	 */
	public static void createInstance() {
		instance = new AsyncTaskManager();
	}

	/**
	 * @return 异步任务管理器实例
	 */
	public static AsyncTaskManager getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return new AsyncTaskManager();
		}
	}

	private AsyncTaskManager() {
		tasks = new ArrayList<>();
	}

	/**
	 * 处在管理中（已被注册的异步任务）
	 */
	private ArrayList<BaseAsyncTask> tasks;

	/**
	 * 注册异步任务
	 *
	 * @param task 需要进行管理的异步任务
	 */
	public void register(BaseAsyncTask task) {
		tasks.add(task);
	}

	/**
	 * 取消注册异步任务
	 *
	 * @param task 需要取消注册的异步任务
	 */
	public void unregister(BaseAsyncTask task) {
		tasks.remove(task);
	}

	/**
	 * 取消相应链接相关的异步任务
	 *
	 * @param tag 任务的标签
	 */
	public void cancel(String tag) {
		// 遍历并取消需要取消的异步任务
		for (BaseAsyncTask task : tasks) {
			if (task.getTag().equals(tag)) {
				task.cancel(true);
			}
		}
	}
}
