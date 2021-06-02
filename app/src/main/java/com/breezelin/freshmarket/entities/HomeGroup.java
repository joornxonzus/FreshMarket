package com.breezelin.freshmarket.entities;

/**
 * Created by Breeze Lin
 * 13:51 2016/5/2
 * 1linyufeng1@gmail.com
 */


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页内容组
 */
public class HomeGroup extends JsonStruct {


	/*
	"FloatingBanners": {
      "Title": "漂浮广告",
      "CountdownSeconds": "",
      "Items": [
        {
          "BannerId": "",
          "BannerName": "",
          "LinkChannel": "",
          "LinkType": "",
          "LinkCode": "",
          "LinkUrl": "",
          "PictureUrl": "",
          "Badge": ""
        }
      ]
    }
	 */

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 倒计时时间（不准备使用）
	 */
	private String countdownSeconds;
	/**
	 * 内容列表
	 */
	private List<HomeContent> items;

	public HomeGroup() {
		items = new ArrayList<>();
	}

	@Override
	public void fromJson(JSONObject jsonObject) {
		title = jsonObject.optString("Title");
		countdownSeconds = jsonObject.optString("CountdownSeconds");
		JSONArray itemsJson = jsonObject.optJSONArray("Items");
		if (itemsJson != null) {
			for (int i = 0; i < itemsJson.length(); i++) {
				HomeContent content = new HomeContent();
				content.fromJson(itemsJson.optJSONObject(i));
				items.add(content);
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public String getCountdownSeconds() {
		return countdownSeconds;
	}

	public List<HomeContent> getItems() {
		return items;
	}
}
