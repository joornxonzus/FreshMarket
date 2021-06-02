package com.breezelin.freshmarket.utils;

/**
 * Created by Breeze Lin
 * 19:07 2016/4/17
 * 1linyufeng1@gmail.com
 */

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换工具类
 */
public final class TimeUtils {

	@SuppressLint("SimpleDateFormat")
	private static final SimpleDateFormat REQUEST_DATE_FORMAT =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @return 网络请求用时间
	 */
	public static String getRequestDate() {

		long time = System.currentTimeMillis();
		return REQUEST_DATE_FORMAT.format(new Date(time));
	}
}
