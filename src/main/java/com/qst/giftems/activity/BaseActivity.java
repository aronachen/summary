package com.qst.giftems.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qst.giftems.App;
import com.qst.giftems.Dialogs;
import com.qst.giftems.Dialogs.OnOkListener;
import com.qst.giftems.MainActivity;
import com.qst.giftems.R;

import java.io.InputStream;

/**
 * Activity父类，包含标题栏和右下角操作区。
 */
public abstract class BaseActivity extends Activity {
    // 顶部标题栏
    protected TextView titleTextView;
    protected View backView; // 返回
    protected View shareView; // 分享
    protected View collectView; // 收藏
    protected View recycleView; // 删除
    protected View helpView; // 帮助
    protected View quitView; // 退出

    // 右下角扇形菜单
    protected RelativeLayout cornerMenuOpenRelativeLayout;
    protected ImageView settingImageView; // 设置
    protected ImageView calendarImageView; // 日历
    protected ImageView personalImageView; // 个人中心
    protected ImageView shoppingBagImageView; // 购物袋
    protected ImageView showCornerMenuImageView; // 展开/收缩

    protected int layoutId; // 布局资源id
    protected String title; // 标题

    protected BaseActivity(int layoutId, String title) {
        this.layoutId = layoutId;
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);

        titleTextView =  findViewById(R.id.titleTextView);
        backView = findViewById(R.id.backView);
        shareView = findViewById(R.id.shareView);
        collectView = findViewById(R.id.collectView);
        recycleView = findViewById(R.id.recycleView);
        helpView = findViewById(R.id.helpView);
        quitView = findViewById(R.id.quitView);

        cornerMenuOpenRelativeLayout = findViewById(R.id.cornerMenuOpenRelativeLayout);
        settingImageView = findViewById(R.id.settingImageView);
        calendarImageView = findViewById(R.id.calendarImageView);
        personalImageView = findViewById(R.id.personalImageView);
        shoppingBagImageView = findViewById(R.id.shoppingBagImageView);
        showCornerMenuImageView = findViewById(R.id.showCornerMenuImageView);

        titleTextView.setText(title);

        backView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        quitView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogs.showSimpleDialog(BaseActivity.this, "您确定退出登录吗？",
                        true, new OnOkListener() {
                            @Override
                            public void onOk() {
                                App app = (App) getApplication();
                                app.user = null;
                                showToast("已退出登录");
                                quitView.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });

        settingImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseActivity.this.getClass() == SettingActivity.class)
                    return;
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        calendarImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 后续章节添加功能
            }
        });
        shoppingBagImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 后续章节添加功能
            }
        });
        personalImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseActivity.this.getClass() == PersonalActivity.class)
                    return;
                if (!checkLogin())
                    return;
                Intent intent = new Intent(getApplicationContext(), PersonalActivity.class);
                startActivity(intent);
            }
        });
        cornerMenuOpenRelativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerMenuOpenRelativeLayout.setVisibility(View.GONE);
                showCornerMenuImageView.setVisibility(View.VISIBLE);
            }
        });
        showCornerMenuImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerMenuOpenRelativeLayout.setVisibility(View.VISIBLE);
                showCornerMenuImageView.setVisibility(View.GONE);
            }
        });
    }

    public void showMessage(String message) {
        Dialogs.showSimpleDialog(this, message, false, null);
    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showNetError() {
        Toast.makeText(getApplicationContext(), "无法连接到服务器，请检查网络后再试。",
                Toast.LENGTH_LONG).show();
    }

    /**
     * 只是检查是否保存了mobileToken，没有到服务器验证
     */
    public boolean checkLogin() {
        App app = (App) getApplication();
        if (app.user == null || app.user.mobileToken == null) {
            Dialogs.showSimpleDialog(this, "登录后才能继续操作，您现在登录吗？", true,
                    new OnOkListener() {
                        @Override
                        public void onOk() {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    });
            return false;
        }
        return true;
    }

    public static Bitmap readBitMapFromResource(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

}
