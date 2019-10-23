package com.qst.giftems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.qst.giftems.App;
import com.qst.giftems.R;
import com.qst.giftems.util.StringUtils;

/**
 * 个人中心
 */
public class PersonalActivity extends BaseActivity {

    Button  myInfoButton, //
            changePasswordButton, //
            userAddressButton,//
            orderButton, //
            pointButton, //
            scanButton, //
            favorityButton;//

    public PersonalActivity() {
        super(R.layout.personal, "个人中心");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myInfoButton = (Button) findViewById(R.id.myInfoButton);
        changePasswordButton = (Button) findViewById(R.id.changePasswordButton);
        userAddressButton = (Button) findViewById(R.id.userAddressButton);
        orderButton = (Button) findViewById(R.id.orderButton);
        pointButton = (Button) findViewById(R.id.pointButton);
        favorityButton = (Button) findViewById(R.id.favorityButton);
        scanButton = (Button) findViewById(R.id.scanButton);

        App app = (App) getApplication();
        if (app.user == null || StringUtils.isEmpty(app.user.mobileToken))
            quitView.setVisibility(View.GONE);
        else
            quitView.setVisibility(View.VISIBLE);

        myInfoButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                Intent intent = new Intent(getApplicationContext(),
                        MyActivity.class);
                startActivity(intent);
            }
        });
        changePasswordButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                Intent intent = new Intent(getApplicationContext(),
                        ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        userAddressButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                // TODO 后续章节实现此功能
                Intent intent = new Intent(getApplicationContext(),
                        UserAddressListActivity.class);
                startActivity(intent);
            }
        });
        orderButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                // TODO 后续章节实现此功能
            }
        });
        pointButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                // TODO 后续章节实现此功能
            }
        });
        favorityButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                // TODO 后续章节实现此功能
                Intent intent = new Intent(getApplicationContext(),
                        FavoriteActivity.class);
                startActivity(intent);
            }
        });
        scanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                // TODO 后续章节实现此功能
            }
        });
    }

}
