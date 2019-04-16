package com.tedu.pdapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by cgb on 12/26/2018.
 */
//
public class IndexFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment_index,null);
        WebView webView= (WebView) view.findViewById(R.id.webView);
        //加载网页
        String pageUrl=PDApplication.SERVER_IP+"/1808index.html";
        webView.loadUrl(pageUrl);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //不加，单击超链接，用手机自带的浏览器打开网页
        //加，单机超链接，用webview打开网页
        webView.setWebViewClient(new WebViewClient());
        return view;


    }
}
