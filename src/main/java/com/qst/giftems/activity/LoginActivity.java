package com.qst.giftems.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qst.giftems.R;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    EditText userNameEditText;
    EditText passwordEditText;
    TextView errorTextView;

    Button loginButton;
    Button registerButton;

    public LoginActivity() {
        super(R.layout.login, "登录");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText =  findViewById(R.id.passwordEditText);
        errorTextView =  findViewById(R.id.errorTextView);
        loginButton =  findViewById(R.id.loginButton);
        registerButton =  findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏键盘
                View view = getWindow().peekDecorView();
                if (view != null) {
                    InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (userName.length() == 0 || password.length() == 0) {
                    errorTextView.setVisibility(View.VISIBLE);
                    return;
                }
                errorTextView.setVisibility(View.INVISIBLE);
                // TODO 后续章节中添加连接服务器验证登录的功能
            }
        });
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
