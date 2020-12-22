package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class DynURLWebViewActivity extends AppCompatActivity {

    Button prev, next, search;
    WebView webView;
    TextInputEditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyn_url_web_view);
        initViews();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.google.com");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) webView.goBack();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward()) webView.goForward();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    webView.loadUrl(url.getText().toString());
                } catch (NullPointerException e) {
                    webView.loadUrl("");
                }

            }
        });
    }

    public void initViews() {
        webView = findViewById(R.id.webview);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        search = findViewById(R.id.search);
        url = findViewById(R.id.url);
    }
}