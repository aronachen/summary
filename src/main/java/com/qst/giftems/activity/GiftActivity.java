package com.qst.giftems.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.qst.giftems.R;
import com.qst.giftems.model.Gift;
import com.qst.giftems.model.GiftStyle;
import com.qst.giftems.util.StringUtils;

/**
 * 礼品
 */
public class GiftActivity extends BaseActivity {

    int giftTypeId;
    int giftId;

    Gift gift;
    GiftStyle selectedGiftStyle;

    RelativeLayout container;

    ViewFlipper picViewFlipper;
    TextView nameTextView;
    RelativeLayout styleRelativeLayout;
    LinearLayout buyLinearLayout;
    EditText quantityEditText;
    ImageView quantityIncreaseImageView;
    ImageView quantityReduceImageView;
    TextView priceTextView;
    TextView introTextView;
    ImageView styleSelectedImageView;

    LinearLayout loadingLinearLayout;
    WebView remarkWebView;

    Button buyButton;
    Button anotherPayButton;
    Button shoppingBagButton;

    OnClickListener styleOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            GiftStyle style = (GiftStyle) v.getTag();
            selectStyle(style);
        }
    };

    public GiftActivity() {
        super(R.layout.gift, "礼品");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = getApplicationContext();
        giftTypeId = getIntent().getIntExtra("giftTypeId", 0);
        giftId = getIntent().getIntExtra("giftId", 0);

        container = findViewById(R.id.container);

        picViewFlipper = findViewById(R.id.picViewFlipper);
        nameTextView = findViewById(R.id.nameTextView);
        styleRelativeLayout = findViewById(R.id.styleRelativeLayout);
        buyLinearLayout = findViewById(R.id.buyLinearLayout);
        quantityEditText = findViewById(R.id.quantityEditText);
        quantityIncreaseImageView = findViewById(R.id.quantityIncreaseImageView);
        quantityReduceImageView = findViewById(R.id.quantityReduceImageView);
        priceTextView = findViewById(R.id.priceTextView);
        introTextView = findViewById(R.id.introTextView);
        styleSelectedImageView = findViewById(R.id.styleSelectedImageView);
        loadingLinearLayout = findViewById(R.id.loadingLinearLayout);
        remarkWebView = findViewById(R.id.remarkWebView);

        buyButton = findViewById(R.id.buyButton);
        anotherPayButton = findViewById(R.id.anotherPayButton);
        shoppingBagButton = findViewById(R.id.shoppingBagButton);

        picViewFlipper.setInAnimation(context, R.anim.in_from_right);
        picViewFlipper.setOutAnimation(context, R.anim.out_to_left);

        WebSettings webSettings = remarkWebView.getSettings();
        webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        collectView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                // TODO 后续章节完成收藏功能
            }
        });

        quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int quantity = Integer.parseInt(quantityEditText.getText().toString());
                    priceTextView.setText((selectedGiftStyle.discount == 0 ? selectedGiftStyle.price :
                                    selectedGiftStyle.discount) * quantity + "");
                } catch (Exception e) {
                    priceTextView.setText("0");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        quantityIncreaseImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int quantity = Integer.parseInt(quantityEditText.getText().toString());
                    quantityEditText.setText(quantity + 1 + "");
                } catch (Exception e) {
                    quantityEditText.setText("1");
                }
            }
        });
        quantityReduceImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int quantity = Integer.parseInt(quantityEditText.getText().toString());
                    if (quantity > 1)
                        quantityEditText.setText(quantity - 1 + "");
                    else
                        quantityEditText.setText("1");
                } catch (Exception e) {
                    quantityEditText.setText("1");
                }
            }
        });

        buyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                if (selectedGiftStyle == null) {
                    showMessage("请先选择一个礼品款式");
                    return;
                }
                int orderCount;
                try {
                    orderCount = Integer.parseInt(quantityEditText.getText().toString());
                    if (orderCount == 0) {
                        showMessage("请输入购买数量");
                        return;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    showMessage("请输入购买数量");
                    return;
                }
                // TODO 后续章节完成购买功能
            }
        });
        anotherPayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!checkLogin())
                    return;
                if (selectedGiftStyle == null) {
                    showMessage("请先选择一个礼品款式");
                    return;
                }
                int orderCount;
                try {
                    orderCount = Integer.parseInt(quantityEditText.getText().toString());
                    if (orderCount == 0) {
                        showMessage("请输入购买数量");
                        return;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    showMessage("请输入购买数量");
                    return;
                }
                // TODO 后续章节完成购买功能
            }
        });
        shoppingBagButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                if (selectedGiftStyle == null) {
                    showMessage("请先选择一个礼品款式");
                    return;
                }
                int orderCount;
                try {
                    orderCount = Integer.parseInt(quantityEditText.getText().toString());
                    if (orderCount == 0) {
                        showMessage("请输入购买数量");
                        return;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    showMessage("请输入购买数量");
                    return;
                }
                // TODO 后续章节完成购买功能
            }
        });

        // TODO 后续章节改为从服务器获取礼品数据
        gift = new Gift();
        gift.id = 1;
        gift.name = "测试礼品";
        gift.remark = "礼品介绍";
        gift.likes = 10;
        gift.sales = 100;
        gift.collected = false;
        int[] pics = { R.drawable.gift_1, R.drawable.gift_2, R.drawable.gift_3 };
        for (int i = 1; i <= 3; i++) {
            GiftStyle gs = new GiftStyle();
            gs.id = i;
            gs.name = "款式 " + i;
            gs.price = 199;
            gs.discount = 169;
            gs.remark = "款式介绍";
            gs.pic1 = pics[i - 1] + "";
            gs.pic2 = R.drawable.gift_0 + "";
            gs.pic3 = R.drawable.gift_0 + "";
            gs.orderNumber = i;
            gs.gift = gift;
            gift.styles.add(gs);
        }

        collectView.setVisibility(View.VISIBLE);
        collectView.setBackgroundResource(gift.collected ? R.drawable.collect2 : R.drawable.collect1);
        selectStyle(gift.styles.get(0));

        nameTextView.setText(gift.name);
        styleRelativeLayout.removeAllViews();
        for (int s = 0; s < gift.styles.size(); s++) {
            GiftStyle st = gift.styles.get(s);

            ImageView iv = new ImageView(context);
            iv.setId(st.id);
            iv.setTag(st);
            iv.setImageBitmap(readBitMapFromResource(GiftActivity.this,
                    Integer.parseInt(st.pic1)));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(120, 120);
            params.leftMargin = 5;
            if (s > 0)
                params.addRule(RelativeLayout.RIGHT_OF, gift.styles.get(s - 1).id);
            styleRelativeLayout.addView(iv, params);
            iv.setOnClickListener(styleOnClickListener);

            TextView tv = new TextView(context);
            tv.setTextAppearance(context, R.style.text1);
            tv.setText(st.name);
            tv.setGravity(Gravity.CENTER);
            tv.setEllipsize(TruncateAt.END);
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params2.addRule(RelativeLayout.BELOW, st.id);
            params2.addRule(RelativeLayout.ALIGN_LEFT, st.id);
            params2.addRule(RelativeLayout.ALIGN_RIGHT, st.id);
            styleRelativeLayout.addView(tv, params2);
        }
        styleRelativeLayout.addView(styleSelectedImageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        remarkWebView.removeAllViews();
        remarkWebView.destroy();
    }

    void selectStyle(GiftStyle style) {
        if (selectedGiftStyle == style)
            return;
        selectedGiftStyle = style;
        Context context = getApplicationContext();
        try {
            int quantity = Integer.parseInt(quantityEditText.getText().toString());
            priceTextView.setText((selectedGiftStyle.discount == 0 ? selectedGiftStyle.price
                            : selectedGiftStyle.discount)
                            * quantity + "");
        } catch (Exception e) {
            priceTextView.setText("0");
        }
        buyLinearLayout.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) styleSelectedImageView.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_TOP, selectedGiftStyle.id);
        params.addRule(RelativeLayout.ALIGN_BOTTOM, selectedGiftStyle.id);
        params.addRule(RelativeLayout.ALIGN_LEFT, selectedGiftStyle.id);
        params.addRule(RelativeLayout.ALIGN_RIGHT, selectedGiftStyle.id);
        styleRelativeLayout.requestLayout();
        styleSelectedImageView.setVisibility(View.VISIBLE);

        picViewFlipper.removeAllViews();
        if (!StringUtils.isEmpty(style.pic1)) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ScaleType.CENTER_CROP);
            iv.setImageBitmap(readBitMapFromResource(GiftActivity.this, Integer.parseInt(style.pic1)));
            picViewFlipper.addView(iv, ViewFlipper.LayoutParams.MATCH_PARENT, ViewFlipper.LayoutParams.MATCH_PARENT);
        }
        if (!StringUtils.isEmpty(style.pic2)) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ScaleType.CENTER_CROP);
            iv.setImageBitmap(readBitMapFromResource(GiftActivity.this, Integer.parseInt(style.pic2)));
            picViewFlipper.addView(iv, ViewFlipper.LayoutParams.MATCH_PARENT, ViewFlipper.LayoutParams.MATCH_PARENT);
        }
        if (!StringUtils.isEmpty(style.pic3)) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ScaleType.CENTER_CROP);
            iv.setImageBitmap(readBitMapFromResource(GiftActivity.this, Integer.parseInt(style.pic3)));
            picViewFlipper.addView(iv, ViewFlipper.LayoutParams.MATCH_PARENT, ViewFlipper.LayoutParams.MATCH_PARENT);
        }
        if (!StringUtils.isEmpty(style.pic4)) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ScaleType.CENTER_CROP);
            iv.setImageBitmap(readBitMapFromResource(GiftActivity.this, Integer.parseInt(style.pic4)));
            picViewFlipper.addView(iv, ViewFlipper.LayoutParams.MATCH_PARENT, ViewFlipper.LayoutParams.MATCH_PARENT);
        }
        if (!StringUtils.isEmpty(style.pic5)) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ScaleType.CENTER_CROP);
            iv.setImageBitmap(readBitMapFromResource(GiftActivity.this, Integer.parseInt(style.pic5)));
            picViewFlipper.addView(iv, ViewFlipper.LayoutParams.MATCH_PARENT, ViewFlipper.LayoutParams.MATCH_PARENT);
        }

        loadingLinearLayout.setVisibility(View.VISIBLE);
        remarkWebView.setVisibility(View.GONE);
        remarkWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                loadingLinearLayout.setVisibility(View.GONE);
                remarkWebView.setVisibility(View.VISIBLE);
            }
        });
        remarkWebView.loadDataWithBaseURL("", style.remark, "text/html",
                "UTF-8", "");
    }
}
