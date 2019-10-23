package com.qst.giftems.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class SimpleWebView extends WebView {

    public SimpleWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SimpleWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleWebView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false; // 不支持任何触摸
    }
}
