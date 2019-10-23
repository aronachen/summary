package com.qst.giftems.activity;

import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qst.giftems.R;

/**
 * 版本更新
 */
public class UpdateActivity extends BaseActivity {

    static final int DOWNLOAD_ERROR = 0;
    static final int DOWNLOAD_PROGRESS = 10;
    static final int DOWNLOAD_COMPLETED = 20;

    TextView updateTextView;
    TextView downloadTextView;
    ProgressBar downloadProgressBar;
    Button okButton;

    boolean isDownloading;
    boolean cancelDownload;

    public UpdateActivity() {
        super(R.layout.update, "版本更新");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updateTextView = (TextView) findViewById(R.id.updateTextView);
        downloadTextView = (TextView) findViewById(R.id.downloadTextView);
        downloadProgressBar = (ProgressBar) findViewById(R.id.downloadProgressBar);
        okButton = (Button) findViewById(R.id.okButton);

        int versionCode = getIntent().getIntExtra("versionCode", 2);
        String versionName = getIntent().getStringExtra("versionName");
        String apkUrl = getIntent().getStringExtra("apkUrl");
        int fileSize = getIntent().getIntExtra("fileSize", 0);
        String intro = getIntent().getStringExtra("intro");

        updateTextView.setText("版本号：" + versionName + "\n\n" + "文件大小："
                + Formatter.formatFileSize(getApplicationContext(), fileSize)
                + "\n\n" + intro);
        // TODO 后续章节完成下载安装功能
    }
}
