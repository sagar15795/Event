package com.dukhabar.youthutsav;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by sysAdmin on 05/02/16.
 */
public class WebViewGen extends Fragment{
    private static final String PARAM1 = "URL";
    private String mUrl;
    private ProgressBar progressBar;

    public static WebViewGen newInstance(String mUrl) {

        Bundle args = new Bundle();
        args.putString(PARAM1,mUrl);
        WebViewGen fragment = new WebViewGen();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl=getArguments().getString(PARAM1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.webviewgen, container, false);
        WebView web = (WebView) layout.findViewById(R.id.webView);
        progressBar = (ProgressBar) layout.findViewById(R.id.progressBar);

        web.setWebViewClient(new myWebClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(mUrl);
        return layout;

    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
    }

}
