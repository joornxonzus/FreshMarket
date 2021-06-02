package com.breezelin.freshmarket.entities;

/**
 * Created by Breeze Lin
 * 8:11 2016/5/2
 * 1linyufeng1@gmail.com
 */

import com.breezelin.freshmarket.utils.LocalDataUtil;
import com.breezelin.freshmarket.utils.SignUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 需要在登录时提交的数据
 */
public class AutoLoginInfo extends JsonStruct {

	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 登录密码
	 */
	private String password;

	public AutoLoginInfo() {
		userName = SignUtil.encrypt(LocalDataUtil.getInstance().get(LocalDataUtil.KEY_USERNAME));
		password = SignUtil.encrypt(LocalDataUtil.getInstance().get(LocalDataUtil.KEY_PASSWORD));
	}

	@Override
	public void fromJson(JSONObject jsonObject) {
	}

	@Override
	public JSONObject toJson() {
		JSONObject ret = new JSONObject();
		try {
			ret.put("UserName", userName);
			ret.put("Password", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
