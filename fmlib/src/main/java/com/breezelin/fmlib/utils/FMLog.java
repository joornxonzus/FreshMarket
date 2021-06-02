package com.breezelin.fmlib.utils;

/**
 * Created by Breeze Lin
 * 18:13 2016/4/5
 * 1linyufeng1@gmail.com
 */

import android.util.Log;

import com.breezelin.fmlib.BuildConfig;
import com.breezelin.fmlib.Constants;

/**
 * 应用内的日志打印类。<br/>
 * 可依据发布版本进行打印的控制。
 */
public final class FMLog {

	private FMLog() {
		// 私有化构造
	}

	/**
	 * 标识当前是否在调试状态的常数<br/>
	 * 来自版本
	 */
	private static final boolean IS_DEBUG = true;

	/**
	 * 应用log的标签<br/>
	 * 现在为应用名
	 */
	private static final String TAG = Constants.APP_NAME;

	/**
	 * 冗余性日志<br/>
	 * 等级1
	 *
	 * @param tag     标签
	 * @param message 内容
	 */
	public static void v(String tag, String message) {
		if (IS_DEBUG) {
			Log.v(TAG, tag + " -> " + message);
		}
	}

	/**
	 * 调试性日志<br/>
	 * 等级2
	 *
	 * @param tag     标签
	 * @param message 内容
	 */
	public static void d(String tag, String message) {
		if (IS_DEBUG) {
			Log.d(TAG, tag + " -> " + message);
		}
	}

	/**
	 * 信息性日志<br/>
	 * 等级3
	 *
	 * @param tag     标签
	 * @param message 内容
	 */
	public static void i(String tag, String message) {
		if (IS_DEBUG) {
			Log.i(TAG, tag + " -> " + message);
		}
	}

	/**
	 * 警告性日志<br/>
	 * 等级4
	 *
	 * @param tag     标签
	 * @param message 内容
	 */
	public static void w(String tag, String message) {
		if (IS_DEBUG) {
			Log.w(TAG, tag + " -> " + message);
		}
	}

	/**
	 * 故障性日志<br/>
	 * 等级5
	 *
	 * @param tag     标签
	 * @param message 内容
	 */
	public static void e(String tag, String message) {
		if (IS_DEBUG) {
			Log.e(TAG, tag + " -> " + message);
		}
	}

	/**
	 * 无类别日志<br/>
	 * 等级未知
	 *
	 * @param tag     标签
	 * @param message 内容
	 */
	public static void wtf(String tag, String message) {
		if (IS_DEBUG) {
			Log.wtf(TAG, tag + " -> " + message);
		}
	}
}
