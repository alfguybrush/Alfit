package com.alfpp.alf.alfplicacion;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class QueEsVo2 extends Fragment {
    public QueEsVo2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_que_es_vo2, container, false);
        WebView mWebView =(WebView) v.findViewById(R.id.webView);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.loadUrl("file:///android_asset/index.html");
        return v;
    }



}
