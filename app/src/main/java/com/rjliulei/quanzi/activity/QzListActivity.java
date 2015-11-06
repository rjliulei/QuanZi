package com.rjliulei.quanzi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.CustomUltimateRecyclerview;
import com.rjliulei.quanzi.R;
import com.rjliulei.quanzi.adapter.SimpleAnimationAdapter;
import com.rjliulei.quanzi.bean.UserPhoneNum;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class QzListActivity extends AppCompatActivity {

	CustomUltimateRecyclerview ultimateRecyclerView;
	SimpleAnimationAdapter simpleRecyclerViewAdapter = null;
	LinearLayoutManager linearLayoutManager;
	StoreHouseHeader storeHouseHeader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qz_list);

		// bmob初始化
		Bmob.initialize(this, "2e4e39168cf2f6183f457cf07b671adb");
		// 使用推送服务时的初始化操作
		BmobInstallation.getCurrentInstallation(this).save();
		// 启动推送服务
		BmobPush.startWork(this, "2e4e39168cf2f6183f457cf07b671adb");

		initView();
	}

	/**
	 * @param menu
	 * @return
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id){
			case R.id.aboutMeMenuItem:
				Intent i = new Intent(this, AboutActivity.class);
				startActivity(i);
				return true;
			case R.id.preferences:
				Intent intent = new Intent(this, SettingsActivity.class);
				startActivity(intent);
				return true;
			case R.id.feedback:
				startActivity(new Intent(this, QzFeedbackActivity.class));
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void initView() {

		final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ultimateRecyclerView = (CustomUltimateRecyclerview) findViewById(R.id.urv_list);
		ultimateRecyclerView.setHasFixedSize(false);
		List<UserPhoneNum> stringList = new ArrayList<>();
		simpleRecyclerViewAdapter = new SimpleAnimationAdapter(stringList);

		for (int i = 0; i < 10; ++i) {
			UserPhoneNum item = new UserPhoneNum();
			item.setComment("你在做什么");
			item.setPhoneNum("15006793599");
			item.setRealName("柳磊" + i);

			stringList.add(item);
		}
		linearLayoutManager = new LinearLayoutManager(this);
		ultimateRecyclerView.setLayoutManager(linearLayoutManager);
		ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);
		ultimateRecyclerView.addItemDividerDecoration(this);
		ultimateRecyclerView.setCustomSwipeToRefresh();

		refreshingString();
	}

	void refreshingString() {
		storeHouseHeader = new StoreHouseHeader(this);
		// header.setPadding(0, 15, 0, 0);

		storeHouseHeader.initWithString("LOADING");
		// header.initWithStringArray(R.array.akta);
		ultimateRecyclerView.mPtrFrameLayout.setHeaderView(storeHouseHeader);
		ultimateRecyclerView.mPtrFrameLayout.addPtrUIHandler(storeHouseHeader);
		ultimateRecyclerView.mPtrFrameLayout.autoRefresh(false);
		ultimateRecyclerView.mPtrFrameLayout.setPtrHandler(new PtrHandler() {
			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
				boolean canbePullDown = PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view2);
				return canbePullDown;
			}

			@Override
			public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
				ptrFrameLayout.postDelayed(new Runnable() {
					@Override
					public void run() {
						// simpleRecyclerViewAdapter.insert("Refresh things",
						// 0);
						// ultimateRecyclerView.scrollBy(0, -50);
						linearLayoutManager.scrollToPosition(0);
						ultimateRecyclerView.mPtrFrameLayout.refreshComplete();
					}
				}, 1800);
			}
		});

	}
}
