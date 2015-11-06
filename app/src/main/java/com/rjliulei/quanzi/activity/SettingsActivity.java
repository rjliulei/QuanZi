package com.rjliulei.quanzi.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.rjliulei.quanzi.R;
import com.rjliulei.quanzi.SettingsFragment;
import com.rjliulei.quanzi.util.FileUtils;
import com.rjliulei.quanzi.util.ToastUtil;
import com.rjliulei.quanzi.util.Util;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.update.AppVersion;

public class SettingsActivity extends AppCompatActivity{

    AnalyticsApplication app;
    Activity context;
    @Override
    protected void onResume() {
        super.onResume();
        app.send(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = this;
        app = (AnalyticsApplication)getApplication();
        String theme = getSharedPreferences(MainActivity.THEME_PREFERENCES, MODE_PRIVATE).getString(MainActivity.THEME_SAVED, MainActivity.LIGHTTHEME);
        if(theme.equals(MainActivity.LIGHTTHEME)){
            setTheme(R.style.CustomStyle_LightTheme);
        }
        else{
            setTheme(R.style.CustomStyle_DarkTheme);
        }
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable backArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        if(backArrow!=null){
            backArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        }

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(backArrow);
        }

        FragmentManager fm= getFragmentManager();
        fm.beginTransaction().replace(R.id.mycontent, new SettingsFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                if(NavUtils.getParentActivityName(this)!=null){
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //上传最新版本apk
    ProgressDialog dialog = null;

    /**
     * 上传最新版本的文件
     *
     * @author liulei
     * @date 2015-10-6 void
     */
    private void uploadLatestVersion() {

        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setTitle("上传中...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        final String path = FileUtils.DOWNLOAD_PATH + "phoneoverheard.apk";

        FileUtils.copyFile(context.getApplicationInfo().sourceDir, path);

        BTPFileResponse response = BmobProFile.getInstance(context).upload(path, new UploadListener() {

            @Override
            public void onError(int arg0, String arg1) {
                // TODO Auto-generated method stub
                dialog.dismiss();

                ToastUtil.showToastShort(context, "上传失败：" + arg1);
            }

            @Override
            public void onSuccess(String fileName, String url, BmobFile file) {
                // TODO Auto-generated method stub

                dialog.dismiss();

                String appUrl = file.getUrl();

                AppVersion version = new AppVersion();
                version.setChannel("bmob");
                version.setIsforce(false);
                version.setPlatform("android");

                version.setTarget_size(new File(path).length() + "");
                version.setVersion(Util.getVersionName(context));
                version.setPath(file);
                version.setVersion_i(Util.getVersionCode(context));

                version.save(context);
            }

            @Override
            public void onProgress(int arg0) {
                // TODO Auto-generated method stub

                dialog.setProgress(arg0);
            }
        });

    }
}
