package com.breezelin.freshmarket.fragments;

/**
 * Created by Breeze Lin
 * 2016/5/6 07:29
 * 1linyufeng1@gmail.com
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.breezelin.freshmarket.R;
import com.breezelin.freshmarket.app.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * 《p/>
 * 商品搜索页
 */
public class SearchFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_search, container, false);
	}

}
