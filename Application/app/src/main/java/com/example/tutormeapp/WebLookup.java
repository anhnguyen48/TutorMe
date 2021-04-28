package com.example.tutormeapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebLookup extends Activity {
    private EditText urlText;
    private Button goButton;
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weblookup_main);

        // instantiate user interface elements
        urlText = (EditText) findViewById(R.id.url_field);
        goButton = (Button) findViewById(R.id.go_button);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);

        //intercept URL loading and load in widget
        webView.setWebViewClient(new WebViewClient(){
            @SuppressWarnings("deprecation")
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

        // Set button to open browser
        goButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                webView.loadUrl("https://www.khanacademy.org/");
            }
        });

        //set listener on EditText
        urlText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    webView.loadUrl(urlText.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    //the back key navigates back to the previous web page
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
