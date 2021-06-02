package com.breezelin.freshmarket.entities;

/**
 * Created by Breeze Lin
 * 14:01 2016/5/2
 * 1linyufeng1@gmail.com
 */

import org.json.JSONObject;

/**
 * 主页内容回应体
 */
public class HomeRsp extends JsonStruct {

	private HomeGroup banners;
	private HomeGroup shortcutIcons;
	private HomeGroup group1;
	private HomeGroup group2;
	private HomeGroup group3;
	private HomeGroup group4;
	private HomeGroup group5;
	private HomeGroup group6;

	// TODO: 2016/5/2 底部还有几个没用上的条目

	@Override
	public void fromJson(JSONObject jsonObject) {
		banners = new HomeGroup();
		shortcutIcons = new HomeGroup();
		group1 = new HomeGroup();
		group2 = new HomeGroup();
		group3 = new HomeGroup();
		group4 = new HomeGroup();
		group5 = new HomeGroup();
		group6 = new HomeGroup();
		banners.fromJson(jsonObject.optJSONObject("Banners"));
		shortcutIcons.fromJson(jsonObject.optJSONObject("ShortcutIcons"));
		group1.fromJson(jsonObject.optJSONObject("Group1"));
		group2.fromJson(jsonObject.optJSONObject("Group2"));
		group3.fromJson(jsonObject.optJSONObject("Group3"));
		group4.fromJson(jsonObject.optJSONObject("Group4"));
		group5.fromJson(jsonObject.optJSONObject("Group5"));
		group6.fromJson(jsonObject.optJSONObject("Group6"));
	}

	public HomeGroup getBanners() {
		return banners;
	}

	public HomeGroup getShortcutIcons() {
		return shortcutIcons;
	}

	public HomeGroup getGroup1() {
		return group1;
	}

	public HomeGroup getGroup2() {
		return group2;
	}

	public HomeGroup getGroup3() {
		return group3;
	}

	public HomeGroup getGroup4() {
		return group4;
	}

	public HomeGroup getGroup5() {
		return group5;
	}

	public HomeGroup getGroup6() {
		return group6;
	}
}
