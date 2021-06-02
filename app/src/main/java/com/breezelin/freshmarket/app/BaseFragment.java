package com.breezelin.freshmarket.app;

/**
 * Created by Breeze Lin
 * 18:38 2016/4/5
 * 1linyufeng1@gmail.com
 */

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * 应用内Fragment的父类
 */
public class BaseFragment extends Fragment {

	/**
	 * 用于打印日志的标签
	 */
	public static String TAG = BaseFragment.class.getSimpleName();

	public BaseFragment() {
		// 必须的公有无参构造
	}

	// TODO: 2016/4/5 特性定制

	/**
	 * 弹出提示
	 *
	 * @param content 提示内容
	 */
	protected void showToast(String content) {
		Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 弹出提示
	 *
	 * @param contentRes 提示内容资源编号
	 */
	protected void showToast(int contentRes) {
		Toast.makeText(getActivity(), contentRes, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示网络请求错误的方法
	 */
	protected void showRequestError() {
		// TODO: 2016/4/17 进行错误处理
	}
}
