package com.breezelin.freshmarket.network.apis;

/**
 * Created by Breeze Lin
 * 18:33 2016/4/17
 * 1linyufeng1@gmail.com
 */

import com.breezelin.fmlib.utils.TextUtils;
import com.breezelin.freshmarket.entities.AppVer;
import com.breezelin.freshmarket.entities.AutoLoginInfo;
import com.breezelin.freshmarket.entities.JsonStruct;
import com.breezelin.freshmarket.entities.network.LaunchSubmit;
import com.breezelin.freshmarket.entities.network.ReqHeader;
import com.breezelin.freshmarket.entities.network.ReqNRsp;
import com.breezelin.freshmarket.network.ApiResolver;
import com.breezelin.freshmarket.utils.LocalDataUtil;

import org.json.JSONObject;

/**
 * 主要请求（区分于模块请求）
 */
public final class MainApi {

	/**
	 * 检查版本（开启的第一个请求）
	 */
	public static final String CHECK_VERSION = "yiguo.mapi.v3.app.launch";
	/**
	 * 自动登录
	 */
	public static final String AUTO_LOGIN = "yiguo.mapi.v3.user.autologin";

	private MainApi() {
		// 封闭构造
	}

	/**
	 * @return 版本检查回应。应当同时替换本地存储的token。
	 */
	public static AppVer checkVersion() {

		ReqHeader header = new ReqHeader(0, 0, CHECK_VERSION);
		LaunchSubmit body = new LaunchSubmit();
		ReqNRsp<LaunchSubmit> req = new ReqNRsp<>(header, body);
		JSONObject jsonObject = ApiResolver.defaultRequest(req);
		if (jsonObject == null) {
			return null;
		}
		ReqNRsp<AppVer> rsp = new ReqNRsp<>(null, new AppVer());
		rsp.fromJson(jsonObject);

		// 获取请求结果后，检查是否有token一同返回
		// 若存在新的token，则与本地的token进行置换
		String token = rsp.getHeader().getToken();
		if (!TextUtils.isEmpty(token)) {
			LocalDataUtil.getInstance().put(LocalDataUtil.KEY_SVR_TOKEN, token);
		}
		return rsp.getBody();
	}

	/**
	 * 自动登陆
	 */
	public static Object autoLogin() {
		ReqHeader header = new ReqHeader(0, 0, AUTO_LOGIN);
		AutoLoginInfo body = new AutoLoginInfo();
		ReqNRsp<AutoLoginInfo> req = new ReqNRsp<>(header, body);
		JSONObject jsonObject = ApiResolver.defaultRequest(req);
		// TODO: 2016/5/2 处理登录拿回的信息
		if (jsonObject == null) {
			return null;
		} else {
			return new Object();
		}
	}
}
