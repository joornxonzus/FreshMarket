package com.breezelin.freshmarket.fragments;

/**
 * Created by Breeze Lin
 * 2016/5/6 07:29
 * 1linyufeng1@gmail.com
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.breezelin.freshmarket.R;
import com.breezelin.freshmarket.adpaters.HomeContentAdapter;
import com.breezelin.freshmarket.app.BaseFragment;
import com.breezelin.freshmarket.entities.HomeContent;
import com.breezelin.freshmarket.entities.HomeRsp;
import com.breezelin.freshmarket.network.TaskCanceler;
import com.breezelin.freshmarket.network.asynctasks.HomeContentTask;

/**
 * A simple {@link Fragment} subclass.
 * <p/>
 * 主页
 */
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
		HomeContentAdapter.HomeContentListener {

	/**
	 * 主页内容适配器
	 */
	private HomeContentAdapter adapter;
	/**
	 * 刷新控件
	 */
	private SwipeRefreshLayout refreshLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);

		// 绑定视图
		refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.layout_refresh);
		// 列表
		RecyclerView contentList = (RecyclerView) rootView.findViewById(R.id.list_content);
		// 设置刷新控件
		refreshLayout.setOnRefreshListener(this);
		refreshLayout.setRefreshing(true);

		// 设置列表
		contentList.setLayoutManager(new LinearLayoutManager(getActivity()));
		adapter = new HomeContentAdapter(getActivity(), this);
		contentList.setAdapter(adapter);
		getHomeContents();

		return rootView;
	}

	/**
	 * 获取主页内容
	 */
	private void getHomeContents() {
		// 获取主页数据
		new HomeContentTask() {
			@Override
			protected void onSuccess(HomeRsp homeRsp) {

				// 处理获取到的主页内容
				adapter.setContents(homeRsp);
				adapter.notifyDataSetChanged();
				refreshLayout.setRefreshing(false);
			}
		}.execute();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// 取消当前页面的请求
		TaskCanceler.cancelHomeRequests();
	}

	@Override
	public void onRefresh() {
		// 列表刷新，重新拉取主页内容
		getHomeContents();
	}

	@Override
	public void onContentClick(HomeContent content) {
		// 内容点击响应
		switch (content.getLinkType()) {
			case HomeContent.LINK_TYPE_ACTIVITY:
				break;
			case HomeContent.LINK_TYPE_APP:
				break;
			case HomeContent.LINK_TYPE_PRODUCT:
				break;
			case HomeContent.LINK_TYPE_SEARCH:
				break;
		}
	}
}
