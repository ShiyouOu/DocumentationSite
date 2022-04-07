package com.sho.documentationsite;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webview;

    // Removes header and footer from the page
    private void RemoveElements(){
        webview.evaluateJavascript("document.getElementsByClassName('navbar')[0].style.display='none';" +
                    "document.getElementsByTagName('footer')[0].style.display='none';", null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setting up Webview to allow javascript
        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setDomStorageEnabled(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());
        getSupportActionBar().hide();

        webview.setWebViewClient(new WebViewClient(){
            // Hide elements before page load
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);
                RemoveElements();
            }
            // Just in case
            @Override
            public void onPageFinished(WebView view, String url)
            {
                RemoveElements();
            }
        });


        // Load our documentation site into web view
        webview.loadUrl("https://www.farmerman.games");
    }
}