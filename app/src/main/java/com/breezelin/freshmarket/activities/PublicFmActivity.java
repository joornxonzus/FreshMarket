package com.breezelin.freshmarket.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.breezelin.freshmarket.app.BaseActivity;

/**
 * 用于承载展示Fragment的共用Activity</p>,都是基于V4 support中的Fragment、Activity
 * <p>
 * 通用调用方式：<br>
 * <pre>
 * Intent intent = new Intent(context,PublicFmActivity.class);
 * intent.putExtra(PublicFmActivity.FLAG_FRAGMENT, Target.class);
 * intent.putExtra(PublicFmActivity.FLAG_ORIENTATION, orientation); //(options)set orientation
 * intent.putExtras(bundle);//（options）set data
 * startActivity(intent); //launch activity
 * </pre>
 * 简单调用方式：<br>
 * {@link PublicFmActivity#openFragment(Context, Class, Bundle) 直接打开Fragment}、
 * {@link PublicFmActivity#openFragmentForResult(Activity, Class, Bundle, int) 打开Fragment，并且需要在当前Activity中处理返回数据}、
 * {@link PublicFmActivity#openFragmentForResult(Fragment, Class, Bundle, int) 打开Fragment，并且需要在当前的Fragment中处理返回数据}
 * <p/>
 * <p>
 * Fragment 如果需要处理按键返回事件，需要实现{@link OnBackListener#onBackPressed()},返回true则是Fragment处理了且不需要Activity处理，一般可以用来禁止返回。
 * <pre>
 *      public boolean onBackPressed() {
 *          // 禁止返回
 *          return true;
 *      }
 * </pre>
 * </p>
 *
 * @author zhangchaoxian@kankan.com
 * @version 1.0
 * @time 2015/11/28
 */
public class PublicFmActivity extends BaseActivity {

	public static final String FLAG_FRAGMENT = "intent_fragment_name";
	/**
	 * 屏幕显示的方向
	 */
	public static final String FLAG_ORIENTATION = "orientation";

	private OnBackListener mListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String fragmentName = getIntent().getStringExtra(FLAG_FRAGMENT);
		if (TextUtils.isEmpty(fragmentName)) {
			throw new IllegalStateException("fragment can not empty or null!");
		}
		setUpOrientation();

		// current full layout
//        FrameLayout frameLayout = new FrameLayout(this);
//        frameLayout.setId(android.R.id.content);
//        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//        setContentView(frameLayout);

		Bundle arguments = getIntent().getExtras();
		Fragment fragment = Fragment.instantiate(this, fragmentName, arguments);

		// 实现了按键返回监听事件处理
		if (fragment instanceof OnBackListener) {
			mListener = (OnBackListener) fragment;
		}

		// load our fragment here
		getSupportFragmentManager()
				.beginTransaction()
				.add(android.R.id.content, fragment)
				.commit();
	}

	//设置Activity显示的方向，默认为竖屏
	@SuppressWarnings("ResourceType")
	private void setUpOrientation() {
		int orientation = getIntent().getIntExtra(FLAG_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //default portrait
		setRequestedOrientation(orientation);
	}

	@Override
	public void onBackPressed() {
		// 按键返回事件先交给当前的Fragment处理，如果Fragment没有处理则Activity默认处理
		if (mListener != null) {
			boolean result = mListener.onBackPressed();
			if (result)
				return;
		}

		// AbsFm类型的退出前调用下exit()
//        Fragment fragment = getSupportFragmentManager().getFragments().get(0);
//        if (fragment instanceof BaseFragment) {
//            ((BaseFragment) fragment).exit();
//        }
		super.onBackPressed();
	}

	/**
	 * 打开指定Fragment页面，不需要响应返回数据
	 *
	 * @param fragment 当前的Fragment
	 * @param cls      需要打开的Fragment类
	 * @param bundle   需要传递的数据
	 */
	public static void openFragment(Fragment fragment, Class<? extends Fragment> cls, Bundle bundle) {
		openFragment(fragment.getActivity(), cls, bundle);
	}

	/**
	 * 打开指定的Fragment页面，不需要响应返回数据
	 *
	 * @param context 当前Activity上下文
	 * @param cls     需要打开的Fragment类
	 * @param bundle  需要传递的数据
	 */
	public static void openFragment(Context context, Class<? extends Fragment> cls, Bundle bundle) {
		Intent intent = new Intent(context, PublicFmActivity.class);
		intent.putExtra(PublicFmActivity.FLAG_FRAGMENT, cls.getName());
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		context.startActivity(intent);
	}

	/**
	 * 关闭所有的Activity,以当前Activity为root
	 *
	 * @param context 当前activity的上下文
	 * @param cls     需要打开的Fragment类
	 * @param bundle  需要传递的数据
	 */
	public static void openFragmentWithNewTask(Context context, Class<? extends Fragment> cls, Bundle bundle) {
		Intent intent = new Intent(context, PublicFmActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.putExtra(PublicFmActivity.FLAG_FRAGMENT, cls.getName());
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		context.startActivity(intent);
	}

	/**
	 * 打开指定Fragment页面，需要响应返回数据，在当前Activity中处理响应结果
	 *
	 * @param activity    当前Activity
	 * @param cls         需要打开的Fragment类
	 * @param bundle      需要传递的数据
	 * @param requestCode 请求响应码
	 */
	public static void openFragmentForResult(Activity activity, Class<? extends Fragment> cls, Bundle bundle, int requestCode) {
		Intent intent = new Intent(activity, PublicFmActivity.class);
		intent.putExtra(PublicFmActivity.FLAG_FRAGMENT, cls.getName());
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivityForResult(intent, requestCode);
	}

	/**
	 * 打开指定Fragment页面，需要响应返回数据，在当前Fragment中处理响应结果
	 *
	 * @param fragment    当前的Fragment
	 * @param cls         需要打开的Fragment类
	 * @param bundle      需要传递的数据
	 * @param requestCode 请求响应码
	 */
	public static void openFragmentForResult(Fragment fragment, Class<? extends Fragment> cls, Bundle bundle, int requestCode) {
		Intent intent = new Intent(fragment.getActivity(), PublicFmActivity.class);
		intent.putExtra(PublicFmActivity.FLAG_FRAGMENT, cls.getName());
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		fragment.startActivityForResult(intent, requestCode);
	}

	/**
	 * 当fragment需要在返回操作中加入功能时，可以设置此监听
	 */
	public interface OnBackListener {
		/**
		 * 监听当前Activity 按键返回事件
		 *
		 * @return true 表示已被Fragment消费了，当前Activity不再处理；
		 */
		boolean onBackPressed();
	}
}
