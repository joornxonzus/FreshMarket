package com.breezelin.freshmarket.entities.network;

/**
 * Created by Breeze Lin
 * 12:22 2016/4/17
 * 1linyufeng1@gmail.com
 */

import android.support.annotation.Nullable;

import com.breezelin.fmlib.utils.FMLog;
import com.breezelin.freshmarket.entities.JsonStruct;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Http请求体
 */
public class ReqNRsp<T extends JsonStruct> extends JsonStruct {

	/**
	 * 请求的头信息
	 */
	private ReqHeader header;
	/**
	 * 请求体，用于提交的信息
	 */
	private T body;

	/**
	 * Http请求体
	 *
	 * @param header 头信息
	 * @param body   请求体
	 */
	public ReqNRsp(ReqHeader header, @Nullable T body) {
		if (header == null) {
			this.header = new ReqHeader();
		} else {
			this.header = header;
		}
		if (body != null) {
			this.body = body;
		} else {
			//noinspection unchecked
			this.body = (T) new JsonStruct() {
				@Override
				public void fromJson(JSONObject jsonObject) {
				}
			};
		}
	}

	@Override
	public void fromJson(JSONObject jsonObject) {
		header = new ReqHeader();
		header.fromJson(jsonObject);
		body.fromJson(jsonObject);
	}

	@Override
	public JSONObject toJson() {
		JSONObject ret = new JSONObject();
		try {
			ret.put("Head", header.toJson());
			ret.put("Body", body.toJson());
		} catch (JSONException e) {
			FMLog.e(TAG, "Json parse error. " + e);
			e.printStackTrace();
		}
		return ret;
	}

	public ReqHeader getHeader() {
		return header;
	}

	public T getBody() {
		return body;
	}
}
