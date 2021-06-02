package com.breezelin.freshmarket.network;

/**
 * Created by Breeze Lin
 * 19:44 2016/4/17
 * 1linyufeng1@gmail.com
 */

import com.breezelin.fmlib.network.HttpUtils;
import com.breezelin.fmlib.utils.FMLog;
import com.breezelin.freshmarket.app.Constants;
import com.breezelin.freshmarket.entities.network.ReqNRsp;
import com.breezelin.freshmarket.utils.SignUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * 访问api的工具类
 */
public class ApiResolver {

	/**
	 * 打印日志用标签
	 */
	private static final String TAG = "ApiResolver";

	/**
	 * api统一请求地址
	 */
	private static final String API_URL = "http://mapi.yiguo.com/MobileApi.aspx";
	/**
	 * api统一请求token
	 */
	private static final String TOKEN = "CA0C4043E9594934BB51BCE0AD9D36EB";
	/**
	 * api统一请求系统
	 */
	private static final String OS = "android";

	/**
	 * 进行通常的请求
	 *
	 * @param req 请求实体
	 * @return 获取到的内容
	 */
	public static JSONObject defaultRequest(ReqNRsp req) {
		JSONObject ret = null;

		String data = req.toJson().toString();

		// 拼参数
		HashMap<String, String> headers = new HashMap<>();
		headers.put("token", TOKEN);
		headers.put("v", Constants.VERSION);
		headers.put("os", OS);
		headers.put("sign", SignUtil.calcSign(data));

		// 进行请求
		try {
			byte[] bytes = HttpUtils.postForByte(API_URL, headers, data.getBytes("utf-8"));
			if (bytes != null) {
				String result = new String(bytes, "utf-8");
				FMLog.i(TAG, "default response -> " + result.substring(0, 100));
				ret = new JSONObject(result);
			}
		} catch (UnsupportedEncodingException e) {
			FMLog.e(TAG, "Data cast error. " + e);
			e.printStackTrace();
		} catch (JSONException e) {
			FMLog.e(TAG, "Json cast error. " + e);
			e.printStackTrace();
		}
		return ret;
	}
}
