package com.breezelin.fmlib.utils;

/**
 * Created by Breeze Lin
 * 18:22 2016/4/17
 * 1linyufeng1@gmail.com
 */

/**
 * 字符串处理工具类
 */
public final class TextUtils {

	// 私有化构造
	private TextUtils() {
	}

	public static boolean isEmpty(String string){
		return string == null || string.length() == 0;
	}
}
