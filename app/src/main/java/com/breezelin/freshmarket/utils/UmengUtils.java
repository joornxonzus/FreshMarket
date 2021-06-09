package com.breezelin.freshmarket.utils;

/**
 * Created by Breeze Lin
 * 2016/5/3 13:34
 * breezesummerlin@163.com
 */

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.Gravity;

import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * 友盟的工具类（分享与分享统计）
 */
public class UmengUtils {

	/**
	 * 分享介质——无
	 */
	public static final int SHARE_MEDIA_NONE = -1;
	/**
	 * 分享介质——微博
	 */
	public static final int SHARE_MEDIA_WEIBO = 0;
	/**
	 * 分享介质——微信
	 */
	public static final int SHARE_MEDIA_WECHAT = 1;
	/**
	 * 分享介质——朋友圈
	 */
	public static final int SHARE_MEDIA_CIRCLE = 2;
	/**
	 * 分享介质——qq
	 */
	public static final int SHARE_MEDIA_QQ = 3;
	/**
	 * 分享介质——qq空间
	 */
	public static final int SHARE_MEDIA_QZONE = 4;
	/**
	 * 分享介质——复制
	 */
	public static final int SHARE_MEDIA_COPY = 5;

	/**
	 * 监听
	 */
	private static UMListener listener;

	/**
	 * 进行分享
	 *
	 * @param activity   activity，用于应用程序相关操作
	 * @param media      分享介质
	 * @param title      标题
	 * @param content    内容
	 * @param cover      封面
	 * @param contentUrl 链接
	 */
	public static void share(Activity activity, int media, String title, String content, String cover,
	                         String contentUrl) {
		share(activity, media, title, content, cover, contentUrl, listener);
	}

	/**
	 * 进行分享
	 *
	 * @param activity   activity，用于应用程序相关操作
	 * @param media      分享介质
	 * @param title      标题
	 * @param content    内容
	 * @param cover      封面
	 * @param contentUrl 链接
	 * @param listener   监听（设置一次，生效一次）
	 */
	public static void share(Activity activity, int media, String title, String content, String cover,
	                         String contentUrl, UMListener listener) {
//		UMSocialService controller = UMServiceFactory.getUMSocialService(Constants.WEIBO_CALLBACK);
//		UmengUtils.listener = listener;
//		switch (media) {
//			case SHARE_MEDIA_CIRCLE:
//				shareOnCircle(activity, controller, title, content, cover, contentUrl);
//				break;
//			case SHARE_MEDIA_COPY:
//				copyToClipboard(activity, contentUrl);
//				break;
//			case SHARE_MEDIA_QQ:
//				shareOnQQ(activity, controller, title, content, cover, contentUrl);
//				break;
//			case SHARE_MEDIA_QZONE:
//				shareOnQzone(activity, controller, title, content, cover, contentUrl);
//				break;
//			case SHARE_MEDIA_WECHAT:
//				shareOnWeChat(activity, controller, title, content, cover, contentUrl);
//				break;
//			case SHARE_MEDIA_WEIBO:
//				shareOnWeibo(activity, controller, title, content, null, cover, contentUrl);
//				break;
//		}
	}

	// 微博分享
	public static void shareOnWeibo(Activity activity, UMSocialService controller, String title,
	                                String content, String weiboContent, String cover, String contentUrl) {
		SinaSsoHandler sinaSsoHandler = new SinaSsoHandler(activity.getApplicationContext());
		controller.getConfig().setSsoHandler(sinaSsoHandler);
		if (weiboContent != null && weiboContent.length() > 0) {
			controller.setShareContent(weiboContent);
		} else {
			controller.setShareContent(title + " " + contentUrl);
		}
		if (cover != null)
			controller.setShareMedia(new UMImage(activity, cover));
		performShare(SHARE_MEDIA.SINA, activity, controller);
	}

	// 微信分享
	private static void shareOnWeChat(Activity activity, UMSocialService controller, String title,
	                                  String content, String cover, String contentUrl) {
		// 添加微信平台
//		UMWXHandler wxHandler = new UMWXHandler(activity,
//				Constants.WECHAT_APP_ID, Constants.WECHAT_APP_SECRET);
//		wxHandler.addToSocialSDK();
//
//		// 设置分享内容
//		WeiXinShareContent weiXinShareContent = new WeiXinShareContent();
//		if (content != null)
//			weiXinShareContent.setShareContent(content);
//		if (title != null)
//			weiXinShareContent.setTitle(title);
//		weiXinShareContent.setTargetUrl(contentUrl);
//		if (cover != null)
//			weiXinShareContent.setShareImage(new UMImage(activity, cover));
//		controller.setShareMedia(weiXinShareContent);
//
//		performShare(SHARE_MEDIA.WEIXIN, activity, controller);
	}

	// 朋友圈分享
	private static void shareOnCircle(Activity activity, UMSocialService controller, String title,
	                                  String content, String cover, String contentUrl) {
		// 支持微信朋友圈
//		UMWXHandler wxCircleHandler = new UMWXHandler(activity,
//				Constants.WECHAT_APP_ID, Constants.WECHAT_APP_SECRET);
//		wxCircleHandler.setToCircle(true);
//		wxCircleHandler.addToSocialSDK();
//
//		// 设置分享内容
//		CircleShareContent circleShareContent = new CircleShareContent();
//		circleShareContent.setTitle(title);
//		circleShareContent.setShareContent(content);
//		circleShareContent.setShareImage(new UMImage(activity, cover));
//		circleShareContent.setTargetUrl(contentUrl);
//		controller.setShareMedia(circleShareContent);
//
//		performShare(SHARE_MEDIA.WEIXIN_CIRCLE, activity, controller);
	}

	// qq分享
	private static void shareOnQQ(Activity activity, UMSocialService controller, String title,
	                              String content, String cover, String contentUrl) {
		// 支持QQ分享
		// 参数1 当前Activity，
		// 参数2 开发者在QQ互联申请的app id，
		// 参数3 开发者在QQ互联申请的app key
//		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity, Constants.QQ_APP_ID,
//				Constants.QQ_APP_SECRET);
//		qqSsoHandler.addToSocialSDK();
//
//		// 设置分享内容
//		QQShareContent qqShareContent = new QQShareContent();
//		qqShareContent.setShareContent(content);
//		qqShareContent.setTitle(title);
//		qqShareContent.setShareImage(new UMImage(activity, cover));
//		qqShareContent.setTargetUrl(contentUrl);
//		controller.setShareMedia(qqShareContent);
//
//		performShare(SHARE_MEDIA.QQ, activity, controller);
	}

	// qq空间分享
	private static void shareOnQzone(Activity activity, UMSocialService controller, String title,
	                                 String content, String cover, String contentUrl) {
//		// 支持QQ空间
//		// 参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
//		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, Constants.QQ_APP_ID,
//				Constants.QQ_APP_SECRET);
//		qZoneSsoHandler.addToSocialSDK();
//
//		// 设置分享内容
//		QZoneShareContent qZoneShareContent = new QZoneShareContent();
//		qZoneShareContent.setShareContent(content);
//		qZoneShareContent.setTargetUrl(contentUrl);
//		qZoneShareContent.setTitle(title);
//		qZoneShareContent.setShareImage(new UMImage(activity, cover));
//		controller.setShareMedia(qZoneShareContent);
//
//		performShare(SHARE_MEDIA.QZONE, activity, controller);
	}

	// 复制到剪贴板
	private static void copyToClipboard(Activity activity, String content) {
		// 拷贝到剪贴板
		ClipboardManager clipboardManager =
				(ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
		//noinspection deprecation
		clipboardManager.setText(content);
//		SToast.show(R.string.share_by_clipboard, Gravity.CENTER);
		if (listener != null) {
			listener.onShareSuccess();
			listener = null;
		}
	}

	// 分享动作执行
	private static void performShare(SHARE_MEDIA platform, final Activity activity,
	                                 UMSocialService controller) {
		controller.postShare(activity, platform, new SocializeListeners.SnsPostListener() {

			@Override
			public void onStart() {

			}

			@Override
			public void onComplete(SHARE_MEDIA platform, int eCode, SocializeEntity entity) {
				if (eCode == StatusCode.ST_CODE_SUCCESSED) {
					// 分享成功,统计分享数据
					statistics(activity, platform);
				}
				if (listener != null) {
					if (eCode == StatusCode.ST_CODE_SUCCESSED) {
						listener.onShareSuccess();
					} else {
						listener.onShareFailed();
					}
					listener = null;
				}

			}
		});
	}

	// 分享统计处理
	private static void statistics(Activity activity, SHARE_MEDIA platform) {
		UMPlatformData.UMedia uMedia = UMPlatformData.UMedia.WEIXIN_FRIENDS;
		switch (platform) {
			case WEIXIN:
				uMedia = UMPlatformData.UMedia.WEIXIN_FRIENDS;
				break;
			case WEIXIN_CIRCLE:
				uMedia = UMPlatformData.UMedia.WEIXIN_CIRCLE;
				break;
			case SINA:
				uMedia = UMPlatformData.UMedia.SINA_WEIBO;
				break;
			case QQ:
				uMedia = UMPlatformData.UMedia.TENCENT_QQ;
				break;
			case QZONE:
				uMedia = UMPlatformData.UMedia.TENCENT_QZONE;
				break;
		}

		String user_id = LocalDataUtil.getInstance().get(LocalDataUtil.KEY_USERNAME);
		UMPlatformData.GENDER gender = UMPlatformData.GENDER.FEMALE;
		UMPlatformData umPlatformData = new UMPlatformData(uMedia, user_id);
		umPlatformData.setGender(gender); //optional
		MobclickAgent.onSocialEvent(activity, umPlatformData);
	}

	/**
	 * 分享监听
	 */
	public interface UMListener {

		/**
		 * 分享成功回调
		 */
		void onShareSuccess();

		/**
		 * 分享失败回调
		 */
		void onShareFailed();
	}
}
