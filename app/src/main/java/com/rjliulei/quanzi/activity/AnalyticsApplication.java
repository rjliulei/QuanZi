package com.rjliulei.quanzi.activity;

import android.app.Application;

import java.util.Map;

public class AnalyticsApplication extends Application {

	private static final boolean IS_ENABLED = true;

	public void send(Object screenName) {
	}

	public void send(Object screenName, Map<String, String> params) {

	}

	@Override
	public void onCreate() {

		instance = this;
		super.onCreate();
	}

	private static AnalyticsApplication instance;

	public static AnalyticsApplication getInstance() {

		return instance;
	}

}
