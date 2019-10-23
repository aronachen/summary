package com.qst.giftems.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qst.giftems.R;

/**
 * 修改密码
 */
public class ChangePasswordActivity extends BaseActivity {

    EditText oldPasswordEditText;
    TextView oldPasswordErrorTextView;
    EditText newPasswordEditText;
    EditText newPassword2EditText;
    TextView newPasswordErrorTextView;
    Button okButton;

    public ChangePasswordActivity() {
        super(R.layout.change_password, "修改密码");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        oldPasswordEditText = (EditText) findViewById(R.id.oldPasswordEditText);
        oldPasswordErrorTextView = (TextView) findViewById(R.id.oldPasswordErrorTextView);
        newPasswordEditText = (EditText) findViewById(R.id.newPasswordEditText);
        newPassword2EditText = (EditText) findViewById(R.id.newPassword2EditText);
        newPasswordErrorTextView = (TextView) findViewById(R.id.newPasswordErrorTextView);
        okButton = (Button) findViewById(R.id.okButton);

    }
}
