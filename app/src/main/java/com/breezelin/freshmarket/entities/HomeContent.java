package com.breezelin.freshmarket.entities;

/**
 * Created by Breeze Lin
 * 13:33 2016/5/2
 * 1linyufeng1@gmail.com
 */

import org.json.JSONObject;

/**
 * 主页条目实体
 */
public class HomeContent extends JsonStruct {

	/**
	 * 链接类型——产品
	 */
	public static final String LINK_TYPE_PRODUCT = "1";
	/**
	 * 链接类型——活动网页
	 */
	public static final String LINK_TYPE_ACTIVITY = "3";
	/**
	 * 链接类型——搜索
	 */
	public static final String LINK_TYPE_SEARCH = "4";
	/**
	 * 链接类型——应用内跳转
	 */
	public static final String LINK_TYPE_APP = "5";

	/*
	{
          "BannerId": "de4b0f5d-4c9b-4d4b-91f0-eaa455662f07",
          "BannerName": "优惠券",
          "LinkChannel": "3",
          "LinkType": "5",
          "LinkCode": "couponlist",
          "LinkUrl": "ygap://app/couponlist.html",
          "PictureUrl": "http://img14.yiguoimg.com/e/albums/2016/160217/153685355498578001_147.png",
          "Badge": ""
        }
	 */

	/**
	 * 该条目的标识
	 */
	private String bannerId;
	/**
	 * 广告条目的文字
	 */
	private String bannerName;
	/**
	 * todo 未知字段
	 */
	private String linkChannel;
	/**
	 * 连接的类型
	 */
	private String linkType;
	/**
	 * 连接附带信息
	 */
	private String linkCode;
	/**
	 * 链接
	 */
	private String linkUrl;
	/**
	 * 图片链接
	 */
	private String pictureUrl;
	/**
	 * todo 未知字段
	 */
	private String badge;

	@Override
	public void fromJson(JSONObject jsonObject) {
		bannerId = jsonObject.optString("BannerId");
		bannerName = jsonObject.optString("BannerName");
		linkChannel = jsonObject.optString("LinkChannel");
		linkType = jsonObject.optString("LinkType");
		linkCode = jsonObject.optString("LinkCode");
		linkUrl = jsonObject.optString("LinkUrl");
		pictureUrl = jsonObject.optString("PictureUrl");
		badge = jsonObject.optString("Badge");
	}

	public String getBannerId() {
		return bannerId;
	}

	public String getBannerName() {
		return bannerName;
	}

	public String getLinkChannel() {
		return linkChannel;
	}

	public String getLinkType() {
		return linkType;
	}

	public String getLinkCode() {
		return linkCode;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public String getBadge() {
		return badge;
	}
}
