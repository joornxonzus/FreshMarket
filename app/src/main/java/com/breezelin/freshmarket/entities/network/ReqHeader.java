package com.breezelin.freshmarket.entities.network;

/**
 * Created by Breeze Lin
 * 12:46 2016/4/17
 * 1linyufeng1@gmail.com
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.breezelin.fmlib.utils.TextUtils;
import com.breezelin.freshmarket.app.Constants;
import com.breezelin.freshmarket.entities.JsonStruct;
import com.breezelin.freshmarket.utils.LocalDataUtil;
import com.breezelin.freshmarket.utils.TimeUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 请求中的头信息
 */
public class ReqHeader extends JsonStruct implements Parcelable {

	/*
	{
        "Version": "3.8",
        "PostTime": "2016-04-17 11:16:02",
        "APICode": "yiguo.mapi.v3.app.launch",
        "PageNo": "1",
        "PageSize": "20",
        "PageCount": "0",
        "UseGZip": "0",
        "RspMsg": "",
        "RspCode": "1",
        "RspFormat": "1",
        "DeviceId": "797871e022dabd51",
        "IMEI": "352430071233061",
        "MobileOS": "1",
        "MobileOSVer": "E6883,5.1.1,22",
        "NetworkType": "wifi",
        "UserName": "",
        "UserId": "",
        "CityCode": "1",
        "CityId": "312d0556-0671-4f2e-8bac-7b8873b5a03a",
        "Token": ""
    }
	 */

	// 无需变更的常量

	/**
	 * 版本号
	 */
	private static final String version = "3.8";
	/**
	 * 页长度
	 */
	private static final String pageSize = String.valueOf(Constants.HTTP_PAGE_SIZE);
	/**
	 * 是否使用压缩（0即代表否）
	 */
	private static final String useGZip = "0";
	/**
	 * 设备id
	 */
	private static final String deviceId = "797871e022dabd51";
	/**
	 * 设备识别码
	 */
	private static final String imei = "352430071233061";
	/**
	 * 设备系统 todo 未知标识
	 */
	private static final String mobileOS = "1";
	/**
	 * 设备系统版本（机型，系统，系统编号）
	 */
	private static final String mobileOsVer = "E6883,5.1.1,22";
	/**
	 * 设备网路类型
	 */
	private static final String networkType = "wifi";

	/**
	 * 请求时间
	 */
	private String postTime;
	/**
	 * 请求的接口
	 */
	private String apiCode;
	/**
	 * 页数
	 */
	private String pageNo;
	/**
	 * todo 未知变量
	 */
	private String pageCount;
	/**
	 * 回复消息
	 */
	private String rspMsg;
	/**
	 * 响应码
	 */
	private String rspCode;
	/**
	 * 回应格式
	 */
	private String rspFormat;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 城市编码
	 */
	private String cityCode;
	/**
	 * 城市id
	 */
	private String cityId;
	/**
	 * token，用作标识 todo 待了解它的生成方式
	 */
	private String token;

	/**
	 * 请求的头信息实体
	 *
	 * @param pageCount todo 未知标识页数
	 * @param pageNo    页数
	 * @param apiCode   请求的地址
	 */
	public ReqHeader(int pageCount, int pageNo, String apiCode) {
		postTime = TimeUtils.getRequestDate();
		this.pageCount = String.valueOf(pageCount);
		this.pageNo = String.valueOf(pageNo);
		this.apiCode = apiCode;
		rspMsg = "";
		rspCode = "";
		rspFormat = "";

		// TODO: 2016/4/17 从Application中获取用户信息，并判断是否存在
		userName = "";
		userId = "";
		// TODO: 2016/4/17 从SharedPreference中获取地址信息，并判断是否存在（不存在则使用默认）
		cityCode = Constants.DEFAULT_CITY_CODE;
		cityId = Constants.DEFAULT_CITY_ID;
		token = LocalDataUtil.getInstance().get(LocalDataUtil.KEY_SVR_TOKEN);
	}

	@Override
	public void fromJson(JSONObject jsonObject) {

		postTime = jsonObject.optString("PostTime");
		apiCode = jsonObject.optString("APICode");
		pageNo = jsonObject.optString("PageNo");
		pageCount = jsonObject.optString("PageCount");
		rspMsg = jsonObject.optString("RspMsg");
		rspCode = jsonObject.optString("RspCode");
		rspFormat = jsonObject.optString("RspFormat");
		userName = jsonObject.optString("UserName");
		userId = jsonObject.optString("UserId");
		cityCode = jsonObject.optString("CityCode");
		cityId = jsonObject.optString("CityId");
		token = jsonObject.optString("Token");
	}

	@Override
	public JSONObject toJson() {

		JSONObject ret = new JSONObject();
		try {
			ret.put("Version", version);
			ret.put("PostTime", postTime);
			ret.put("APICode", apiCode);
			ret.put("PageNo", pageNo);
			ret.put("PageSize", pageSize);
			ret.put("PageCount", pageCount);
			ret.put("UseGZip", useGZip);
			ret.put("RspMsg", rspMsg);
			ret.put("RspCode", rspCode);
			ret.put("RspFormat", rspFormat);
			ret.put("DeviceId", deviceId);
			ret.put("IMEI", imei);
			ret.put("MobileOS", mobileOS);
			ret.put("MobileOSVer", mobileOsVer);
			ret.put("NetworkType", networkType);
			ret.put("UserName", userName);
			ret.put("UserId", userId);
			ret.put("CityCode", cityCode);
			ret.put("CityId", cityId);
			ret.put("Token", token);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public int getPageNo() {
		return TextUtils.isEmpty(pageNo) ? 0 : Integer.valueOf(pageNo);
	}

	public void setPageNo(int pageNo) {
		this.pageNo = String.valueOf(pageNo);
	}

	public int getPageCount() {
		return TextUtils.isEmpty(pageCount) ? 0 : Integer.valueOf(pageCount);
	}

	public void setPageCount(int pageCount) {
		this.pageCount = String.valueOf(pageCount);
	}

	public String getRspMsg() {
		return rspMsg;
	}

	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}

	public String getRspCode() {
		return rspCode;
	}

	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	public String getRspFormat() {
		return rspFormat;
	}

	public void setRspFormat(String rspFormat) {
		this.rspFormat = rspFormat;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.postTime);
		dest.writeString(this.apiCode);
		dest.writeString(this.pageNo);
		dest.writeString(this.pageCount);
		dest.writeString(this.rspMsg);
		dest.writeString(this.rspCode);
		dest.writeString(this.rspFormat);
		dest.writeString(this.userName);
		dest.writeString(this.userId);
		dest.writeString(this.cityCode);
		dest.writeString(this.cityId);
		dest.writeString(this.token);
	}

	public ReqHeader() {
	}

	protected ReqHeader(Parcel in) {
		this.postTime = in.readString();
		this.apiCode = in.readString();
		this.pageNo = in.readString();
		this.pageCount = in.readString();
		this.rspMsg = in.readString();
		this.rspCode = in.readString();
		this.rspFormat = in.readString();
		this.userName = in.readString();
		this.userId = in.readString();
		this.cityCode = in.readString();
		this.cityId = in.readString();
		this.token = in.readString();
	}

	public static final Parcelable.Creator<ReqHeader> CREATOR = new Parcelable.Creator<ReqHeader>() {
		@Override
		public ReqHeader createFromParcel(Parcel source) {
			return new ReqHeader(source);
		}

		@Override
		public ReqHeader[] newArray(int size) {
			return new ReqHeader[size];
		}
	};
}
