package com.qst.giftems.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.qst.giftems.R;
import com.qst.giftems.model.Strategy;
import com.qst.giftems.util.StringUtils;

/**
 * 礼品攻略
 */
public class StrategyActivity extends BaseActivity {
    int strategyId;
    Strategy strategy;

    ViewFlipper viewFlipper;
    TextView nameTextView;
    TextView remarkTextView;
    LinearLayout loadingLinearLayout;
    WebView contentWebView;

    public StrategyActivity() {
        super(R.layout.strategy, "礼品攻略");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();
        strategyId = getIntent().getIntExtra("strategyId", 0);
        viewFlipper = findViewById(R.id.viewFlipper);
        nameTextView = findViewById(R.id.nameTextView);
        remarkTextView = findViewById(R.id.remarkTextView);
        loadingLinearLayout = findViewById(R.id.loadingLinearLayout);
        contentWebView = findViewById(R.id.contentWebView);

        viewFlipper.setInAnimation(context, R.anim.in_from_right);
        viewFlipper.setOutAnimation(context, R.anim.out_to_left);

        WebSettings webSettings = contentWebView.getSettings();
        webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        // TODO 后续章节改为从服务器获取礼品数据
        strategy = new Strategy();
        strategy.id = 1;
        strategy.title = "测试礼品攻略";
        strategy.remark = "礼品攻略礼品攻略礼品攻略礼品攻略礼品攻略礼品攻略礼品攻略礼品攻略礼品攻略礼品攻略礼品攻略礼品攻略";
        strategy.pic1 = R.drawable.strategy_1 + "";
        strategy.pic2 = R.drawable.strategy_2 + "";
        strategy.pic3 = R.drawable.strategy_3 + "";
        strategy.pic4 = R.drawable.strategy_4 + "";
        strategy.content = "<div style=\"font-size:80pt;background-color:green;color:red\">这是HTML内容</div>";

        if (!StringUtils.isEmpty(strategy.pic1)) {
            ImageView v = new ImageView(context);
            viewFlipper.addView(v);
            v.setImageBitmap(readBitMapFromResource(StrategyActivity.this,
                    Integer.parseInt(strategy.pic1)));
        }
        if (!StringUtils.isEmpty(strategy.pic2)) {
            ImageView v = new ImageView(context);
            viewFlipper.addView(v);
            v.setImageBitmap(readBitMapFromResource(StrategyActivity.this,
                    Integer.parseInt(strategy.pic2)));
        }
        if (!StringUtils.isEmpty(strategy.pic3)) {
            ImageView v = new ImageView(context);
            viewFlipper.addView(v);
            v.setImageBitmap(readBitMapFromResource(StrategyActivity.this,
                    Integer.parseInt(strategy.pic3)));
        }
        if (!StringUtils.isEmpty(strategy.pic4)) {
            ImageView v = new ImageView(context);
            viewFlipper.addView(v);
            v.setImageBitmap(readBitMapFromResource(StrategyActivity.this,
                    Integer.parseInt(strategy.pic4)));
        }
        if (!StringUtils.isEmpty(strategy.pic5)) {
            ImageView v = new ImageView(context);
            viewFlipper.addView(v);
            v.setImageBitmap(readBitMapFromResource(StrategyActivity.this,
                    Integer.parseInt(strategy.pic5)));
        }

        viewFlipper.setAutoStart(viewFlipper.getChildCount() > 1);
        nameTextView.setText(strategy.title);
        remarkTextView.setText(strategy.remark);

        loadingLinearLayout.setVisibility(View.VISIBLE);
        contentWebView.setVisibility(View.GONE);
        contentWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                loadingLinearLayout.setVisibility(View.GONE);
                contentWebView.setVisibility(View.VISIBLE);
            }
        });
        contentWebView.loadDataWithBaseURL("", strategy.content, "text/html",
                "UTF-8", "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contentWebView.removeAllViews();
        contentWebView.destroy();
    }
}
