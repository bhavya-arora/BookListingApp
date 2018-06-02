package com.bhavya.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class bookListView extends AppCompatActivity {

    private WebView webVw =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_view);

        String infoUrl = getIntent().getStringExtra("infoUrl");

        this.webVw = (WebView) findViewById(R.id.webvw);

        webVw.setWebViewClient(new webViewCLient());

        WebSettings settings = webVw.getSettings();
        settings.setJavaScriptEnabled(true);

        webVw.loadUrl(infoUrl);



    }

    public class webViewCLient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }

}
