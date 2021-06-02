package com.breezelin.freshmarket.entities.network;

/**
 * Created by Breeze Lin
 * 16:56 2016/4/19
 * 1linyufeng1@gmail.com
 */

import com.breezelin.freshmarket.entities.JsonStruct;
import com.breezelin.freshmarket.utils.LocalDataUtil;
import com.breezelin.freshmarket.utils.SignUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 开启请求的body
 */
public class LaunchSubmit extends JsonStruct {

	/*
	 "RegisterChannel": "",
        "UserName": "",
        "OpenId": "",
        "AdChannelName": "",
        "Password": ""
	 */
	/**
	 * 注册渠道
	 */
	public String registerChannel;
	/**
	 * 用户名
	 */
	public String userName;
	/**
	 * todo 未知字段，可能是用户的id
	 */
	public String openId;
	/**
	 * todo 未知字段，广告渠道
	 */
	public String adChannelName;
	/**
	 * 密码
	 */
	public String password;

	public LaunchSubmit() {
		registerChannel = "";
		userName = SignUtil.encrypt(LocalDataUtil.getInstance().get(LocalDataUtil.KEY_USERNAME));
		openId = "";
		adChannelName = "";
		password = SignUtil.encrypt(LocalDataUtil.getInstance().get(LocalDataUtil.KEY_PASSWORD));
	}

	@Override
	public void fromJson(JSONObject jsonObject) {

	}

	@Override
	public JSONObject toJson() {

		JSONObject ret = new JSONObject();
		try {
			ret.put("RegisterChannel", registerChannel);
			ret.put("UserName", userName);
			ret.put("OpenId", openId);
			ret.put("AdChannelName", adChannelName);
			ret.put("Password", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
