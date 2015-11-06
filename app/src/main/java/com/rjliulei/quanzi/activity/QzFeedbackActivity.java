package com.rjliulei.quanzi.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.rjliulei.quanzi.R;

//import com.google.android.gms.analytics.HitBuilders;
//import com.google.android.gms.analytics.Tracker;

public class QzFeedbackActivity extends AppCompatActivity {

	private FloatingActionButton mToDoSendFloatingActionButton;
	private Toolbar mToolbar;
	private AnimatedCircleLoadingView aclvLoading;
	AnalyticsApplication app;

	@Override
	protected void onResume() {
		super.onResume();
		app.send(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		app = (AnalyticsApplication) getApplication();

		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_add_to_do);
		// Testing out a new layout
		setContentView(R.layout.activity_feedback);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		// Show an X in place of <-
		final Drawable cross = getResources().getDrawable(R.drawable.ic_clear_white_24dp);
		if (cross != null) {
			cross.setColorFilter(getResources().getColor(R.color.icons), PorterDuff.Mode.SRC_ATOP);
		}

		if (getSupportActionBar() != null) {
			getSupportActionBar().setElevation(0);
			getSupportActionBar().setDisplayShowTitleEnabled(false);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeAsUpIndicator(cross);

		}

		mToDoSendFloatingActionButton = (FloatingActionButton) findViewById(R.id.makeToDoFloatingActionButton);

		mToDoSendFloatingActionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				aclvLoading.startIndeterminate();

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						aclvLoading.stopOk();
					}
				}).start();
			}
		});

		aclvLoading = (AnimatedCircleLoadingView)findViewById(R.id.aclv_loading);

	}

	public void hideKeyboard(EditText et) {

		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(this) != null) {
				NavUtils.navigateUpFromSameTask(this);
			}
//			hideKeyboard(mToDoTextBodyEditText);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
