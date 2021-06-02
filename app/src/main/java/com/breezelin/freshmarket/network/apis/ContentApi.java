package com.breezelin.freshmarket.network.apis;

/**
 * Created by Breeze Lin
 * 13:01 2016/5/2
 * 1linyufeng1@gmail.com
 */

import com.breezelin.freshmarket.entities.HomeRsp;
import com.breezelin.freshmarket.entities.JsonStruct;
import com.breezelin.freshmarket.entities.network.ReqHeader;
import com.breezelin.freshmarket.entities.network.ReqNRsp;
import com.breezelin.freshmarket.network.ApiResolver;

import org.json.JSONObject;

/**
 * 内容相关请求
 */
public final class ContentApi {
	private ContentApi() {
		// 私有构造
	}

	/**
	 * 主页内容获取
	 */
	public static final String HOME_CONTENTS = "yiguo.mapi.v3.app.home.get";

	/**
	 * @return 主页内容
	 */
	public static HomeRsp getHomeContents() {
		ReqHeader header = new ReqHeader(0, 1, HOME_CONTENTS);
		ReqNRsp<JsonStruct> req = new ReqNRsp<>(header, null);
		JSONObject jsonObject = ApiResolver.defaultRequest(req);
		if (jsonObject == null) {
			return null;
		}
		ReqNRsp<HomeRsp> rsp = new ReqNRsp<>(null, new HomeRsp());
		rsp.fromJson(jsonObject.optJSONObject("Body"));
		return rsp.getBody();
	}
}
