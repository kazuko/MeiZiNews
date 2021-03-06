package com.ms.meizinewsapplication.features.main.iview;

import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.view.iview.ColorfuWeblViewImpl;

/**
 * Created by 啟成 on 2016/3/22.
 */
public class DevWeekDetailIVew extends ColorfuWeblViewImpl {


    private FrameLayout webContainer;
    private WebView webView;
    private ProgressBar progress;
    private Toolbar toolbar;


    @Override
    public void created() {
        super.created();
        webContainer = findViewById(R.id.web_container);
        progress = findViewById(R.id.progress);
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.web_view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.app_bar_dev_week_detail;
    }


    public void onPause() {
        webView.onPause();

    }

    public void onResume() {
        webView.onResume();
    }

    public void onBackPressed() {
        webView.setVisibility(View.GONE);
        webContainer.removeAllViews();
    }

    public boolean onCreateOptionsMenu(AppCompatActivity activity, Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        activity.getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    //TODO init =================================================


    @Override
    public void init(AppCompatActivity activity) {

        initToolbar(activity);
        initWebView(activity);
        super.init(activity);
    }

    /**
     * 设置左上角的返回键与它的点击效果
     *
     * @param appCompatActivity
     */
    private void initToolbar(final AppCompatActivity appCompatActivity) {

        toolbar.setTitle(appCompatActivity.getIntent().getStringExtra("title"));
        appCompatActivity.setSupportActionBar(toolbar);

        if (appCompatActivity.getSupportActionBar() == null) {
            return;
        }

        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        [android：ToolBar详解（手把手教程）](http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1118/2006.html)
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    appCompatActivity.finishAfterTransition();
                } else {
                    appCompatActivity.finish();
                }
            }
        });


    }

    public void setMenuItemIconByCollect(boolean isCollect) {

        if (isCollect) {
            setMenuItemIcon(2, R.drawable.iconfont_weishoucang);
        } else {
            setMenuItemIcon(2, R.drawable.iconfont_yishoucang);
        }

    }

    public void setMenuItemIcon(int index, @DrawableRes int iconRes) {
        toolbar.getMenu().getItem(index).setIcon(iconRes);
    }

    public void initWebView(AppCompatActivity activity) {
        webView.setVisibility(View.INVISIBLE);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(final WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            progressGone();
                            view.setVisibility(View.VISIBLE);
                        }
                    }, 300);
                }
            }
        });

        webView.setWebViewClient(new MyWebViewClient());
    }

    public void progressGone() {
        progress.setVisibility(View.GONE);
    }


    //TODO Mode ===================================================

    public void showDetail(String body) {
        String html = dayTheme() + "<html><head></head><body>" + body + "</body></html>";
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }


    //TODO Listener====================

    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener) {

        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

}
