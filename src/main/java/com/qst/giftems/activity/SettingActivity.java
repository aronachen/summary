package com.qst.giftems.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.qst.giftems.R;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity {

    TextView remindOpenTextView;
    TextView remindCloseTextView;
    TextView remindAlermTextView;
    TextView remindVibratorTextView;
    TextView currentVersionNameTextView;
    Button checkUpdateButton;
    Button aboutButton;

    public SettingActivity() {
        super(R.layout.setting, "系统设置");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        remindOpenTextView = findViewById(R.id.remindOpenTextView);
        remindCloseTextView = findViewById(R.id.remindCloseTextView);
        remindAlermTextView = findViewById(R.id.remindAlermTextView);
        remindVibratorTextView = findViewById(R.id.remindVibratorTextView);
        currentVersionNameTextView = findViewById(R.id.currentVersionNameTextView);
        checkUpdateButton = findViewById(R.id.checkUpdateButton);
        aboutButton = findViewById(R.id.aboutButton);

        boolean remind = true; // TODO 后续章节将配置信息存入文件系统
        if (remind) {
            remindOpenTextView.setBackgroundResource(R.color.title_bottom);
            remindCloseTextView.setBackgroundResource(R.drawable.background_border);
        } else {
            remindOpenTextView.setBackgroundResource(R.drawable.background_border);
            remindCloseTextView.setBackgroundResource(R.color.title_bottom);
        }
        String remindType = "alerm"; // TODO 后续章节将配置信息存入文件系统
        if (remindType.equals("alerm")) {
            remindAlermTextView.setBackgroundResource(R.color.title_bottom);
            remindVibratorTextView.setBackgroundResource(R.drawable.background_border);
        } else if (remindType.equals("vibrator")) {
            remindAlermTextView.setBackgroundResource(R.drawable.background_border);
            remindVibratorTextView.setBackgroundResource(R.color.title_bottom);
        }

        try {
            PackageInfo packInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            currentVersionNameTextView.setText("当前版本：" + packInfo.versionName);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            currentVersionNameTextView.setText("");
        }

        remindOpenTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remindOpenTextView.setBackgroundResource(R.color.title_bottom);
                remindCloseTextView.setBackgroundResource(R.drawable.background_border);
                // TODO 后续章节将配置信息存入文件系统
            }
        });
        remindCloseTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remindOpenTextView.setBackgroundResource(R.drawable.background_border);
                remindCloseTextView.setBackgroundResource(R.color.title_bottom);
                // TODO 后续章节将配置信息存入文件系统
            }
        });

        remindAlermTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remindAlermTextView.setBackgroundResource(R.color.title_bottom);
                remindVibratorTextView.setBackgroundResource(R.drawable.background_border);
                // TODO 后续章节将配置信息存入文件系统
            }
        });
        remindVibratorTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remindAlermTextView.setBackgroundResource(R.drawable.background_border);
                remindVibratorTextView.setBackgroundResource(R.color.title_bottom);
                // TODO 后续章节将配置信息存入文件系统
            }
        });

        checkUpdateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getPackageManager();
                try {
                    PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
                    // TODO 后续章节连接服务器检查更新
                    Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                    intent.putExtra("versionCode", 2);
                    intent.putExtra("versionName", "2.0");
                    intent.putExtra("apkUrl", "http://aaa.bbb.ccc/giftems2.0.apk");
                    intent.putExtra("fileSize", 10000000);
                    intent.putExtra("intro", "这是模拟数据，后续章节改为从服务器获取新版本。");
                    startActivity(intent);
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                    showToast("您使用的已是最新版本");
                }
            }
        });

        aboutButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });
    }

}
