package com.breezelin.freshmarket.entities;

/**
 * Created by Breeze Lin
 * 12:25 2016/4/17
 * 1linyufeng1@gmail.com
 */

import org.json.JSONObject;

/**
 * 可以json化的实体。<br/>
 * 网络请求相关的实体应实现此方法
 */
public abstract class JsonStruct {

	/**
	 * 用于打印日志的标签
	 */
	protected String TAG = JsonStruct.class.getSimpleName();

	/**
	 * 将json数据解析为实体
	 *
	 * @param jsonObject 相应的json实体
	 */
	public abstract void fromJson(JSONObject jsonObject);

	/**
	 * 将实体解析为json数据<br/>
	 * 因为不是所有实体都需要，所以不强制继承
	 *
	 * @return 返回相应的json实体
	 */
	public JSONObject toJson() {
		return new JSONObject();
	}
}
