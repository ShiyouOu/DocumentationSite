package com.sho.documentationsite;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private Button homeButton;
    private Button aboutButton;
    private Button teamButton;
    private Button docsButton;


    // Removes header and footer from the page
    private void RemoveElements(){
        webview.evaluateJavascript(
        //        "document.getElementsByClassName('navbar')[0].style.display='none';" +
                   "document.getElementsByTagName('footer')[0].style.display='none';"
        //        + "document.getElementsByTagName('body')[0].style.backgroundColor='#0a0a0a';"
                , null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up buttons
        homeButton = (Button) findViewById(R.id.btnHome);
        aboutButton = (Button) findViewById(R.id.btnAbout);
        teamButton = (Button) findViewById(R.id.btnTeam);
        docsButton = (Button) findViewById(R.id.btnDocs);

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

        // Button Clicks
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                webview.loadUrl("https://www.farmerman.games");
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("https://www.farmerman.games/about/");
            }
        });

        teamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("https://www.farmerman.games/team/");
            }
        });
        docsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("https://www.farmerman.games/documentation/");
            }
        });
        // Load our documentation site into web view
        webview.loadUrl("https://www.farmerman.games");
    }
}