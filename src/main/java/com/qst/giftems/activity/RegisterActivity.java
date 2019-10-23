package com.qst.giftems.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qst.giftems.R;
import com.qst.giftems.util.StringUtils;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity {

    EditText userNameEditText;
    TextView userNameErrorTextView;
    EditText passwordEditText;
    EditText password2EditText;
    TextView passwordErrorTextView;
    EditText mobileEditText;
    TextView mobileErrorTextView;
    EditText validateCodeEditText;
    Button sendValidateCodeButton;
    Button okButton;

    public RegisterActivity() {
        super(R.layout.register, "注册账号");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userNameEditText = findViewById(R.id.userNameEditText);
        userNameErrorTextView = findViewById(R.id.userNameErrorTextView);
        passwordEditText = findViewById(R.id.passwordEditText);
        password2EditText = findViewById(R.id.password2EditText);
        passwordErrorTextView = findViewById(R.id.passwordErrorTextView);
        mobileEditText = findViewById(R.id.mobileEditText);
        mobileErrorTextView = findViewById(R.id.mobileErrorTextView);
        validateCodeEditText = findViewById(R.id.validateCodeEditText);
        sendValidateCodeButton = findViewById(R.id.sendValidateCodeButton);
        okButton = findViewById(R.id.okButton);

        userNameEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String userName = userNameEditText.getText().toString();
                    if (userName.length() == 0) {
                        userNameErrorTextView.setText("请输入用户名");
                        return;
                    }
                    if (userName.length() < 3) {
                        userNameErrorTextView.setText("用户名长度至少3位");
                        return;
                    }
                }
            }
        });
        OnFocusChangeListener ofcl = new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String password = ((EditText) v).getText().toString();
                    if (password.length() < 6) {
                        passwordErrorTextView.setText("密码长度至少6位");
                    } else {
                        passwordErrorTextView.setText("");
                    }
                }
            }
        };
        passwordEditText.setOnFocusChangeListener(ofcl);
        password2EditText.setOnFocusChangeListener(ofcl);
        mobileEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String mobile = mobileEditText.getText().toString();
                    if (!StringUtils.isMobile(mobile)) {
                        mobileErrorTextView.setText("请输入正确的手机号码");
                        return;
                    }
                }
            }
        });

        sendValidateCodeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = mobileEditText.getText().toString();
                if (!StringUtils.isMobile(mobile)) {
                    showMessage("请输入正确的手机号码");
                    return;
                }
            }
        });

        okButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEditText.getText().toString();
                if (userName.length() == 0) {
                    showMessage("请输入用户名");
                    return;
                }
                if (userName.length() < 3) {
                    showMessage("用户名长度至少3位");
                    return;
                }

                String password = passwordEditText.getText().toString();
                String password2 = password2EditText.getText().toString();
                if (!password.equals(password2)) {
                    showMessage("密码两次输入不一致");
                    return;
                }
                if (password.length() < 6) {
                    showMessage("密码长度至少6位");
                    return;
                }

                String mobile = mobileEditText.getText().toString();
                if (!StringUtils.isMobile(mobile)) {
                    showMessage("请输入正确的手机号码");
                    return;
                }

                String validateCode = validateCodeEditText.getText().toString();
                if (validateCode.length() == 0) {
                    showMessage("请输入您收到的验证码");
                    return;
                }
                // TODO 后续章节添加向服务器发送注册数据的功能
            }
        });
    }
}
