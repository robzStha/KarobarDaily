package com.app.karobardainik.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.karobardainik.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webview;
    private String articleId, articleTitle;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_web_view;
//    }
//
//    @Override
//    protected String actionBarTitle() {
//        return getString(R.string.website);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        this.webview = (WebView) findViewById(R.id.webview);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.pb_loading);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//        webview.setWebChromeClient(new WebChromeClient(){
//            public void onProgressChanged(WebView view, int progress){
//                progressBar.setProgress(progress*100);
//                System.out.println("Progress: "+progress+" -- "+progress*100);
//            }
//        });

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            articleId = bundle.getString("article_id");
            articleTitle = bundle.getString("article_title");
        }
        getSupportActionBar().setTitle(articleTitle);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebViewActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                builder.setTitle("Error");
                builder.setMessage(description);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        });

        webview.loadUrl("http://dev.srssolutions.com.np/karobar/api/news/"+articleId);

    }


}
