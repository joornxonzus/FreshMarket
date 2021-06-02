package com.breezelin.freshmarket.utils;

/**
 * Created by Breeze Lin
 * 22:42 2016/4/26
 * 1linyufeng1@gmail.com
 */

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用于管理一些本地存储内容的工具类
 */
public class LocalDataUtil {

	/**
	 * 本地内容实体类
	 */
	private static LocalDataUtil instance;

	/**
	 * 创建实例的方法。运行于程序启动时。
	 *
	 * @param context 用于获取资源文件的上下文
	 */
	public static void createInstance(Context context) {
		instance = new LocalDataUtil(context);
	}

	/**
	 * 获取实例的方法
	 */
	public static LocalDataUtil getInstance() {
		return instance;
	}


	/**
	 * 键——用户名
	 */
	public static final String KEY_USERNAME = "username";
	/**
	 * 键——密码
	 */
	public static final String KEY_PASSWORD = "password";
	/**
	 * 键——服务器返回的token
	 */
	public static final String KEY_SVR_TOKEN = "token";
	/**
	 * 是否第一次开启
	 */
	public static final String KEY_FIRST_OPEN = "first_open";


	/**
	 * 本地数据文件名
	 */
	private static final String DATA_KEY = "main_data";
	/**
	 * 上下文，用于获取资源文件
	 */
	private Context context;

	/**
	 * 实例创建方法
	 *
	 * @param context 用于操作资源的上下文
	 */
	private LocalDataUtil(Context context) {
		this.context = context;
	}

	/**
	 * 获取本地资源文件
	 *
	 * @return 本地资源文件
	 */
	private SharedPreferences getSharedPreference() {
		return context.getSharedPreferences(DATA_KEY, Context.MODE_PRIVATE);
	}

	/**
	 * 获取相应的条目
	 *
	 * @param key 相应本地存储条目对应的键
	 * @return 本地存储的数据条目
	 */
	public String get(String key) {
		String ret;
		SharedPreferences sharedPreferences = getSharedPreference();
		ret = sharedPreferences.getString(key, "");
		return ret;
	}

	/**
	 * 删减相应条目
	 *
	 * @param key   相应本地条目对应的键
	 * @param value 想要存储的值
	 * @return 操作是否成功
	 */
	public boolean put(String key, String value) {
		SharedPreferences sharedPreference = getSharedPreference();
		sharedPreference.edit().putString(key, value).apply();
		return true;
	}
}
