package com.breezelin.freshmarket.entities;

/**
 * Created by Breeze Lin
 * 18:15 2016/4/17
 * 1linyufeng1@gmail.com
 */

import org.json.JSONObject;

/**
 * 软件版本
 */
public class AppVer extends JsonStruct {

	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 信息
	 */
	private String info;
	/**
	 * 升级下载地址
	 */
	private String url;
	/**
	 * 是否进行强制升级
	 */
	private String forceUpdate;

	@Override
	public void fromJson(JSONObject jsonObject) {
		version = jsonObject.optString("Version");
		info = jsonObject.optString("Info");
		url = jsonObject.optString("Url");
		forceUpdate = jsonObject.optString("ForceUpdate");
	}

	public String getVersion() {
		return version;
	}

	public String getInfo() {
		return info;
	}

	public String getUrl() {
		return url;
	}

	public boolean getForceUpdate() {
		return forceUpdate.equals("1");
	}
}
