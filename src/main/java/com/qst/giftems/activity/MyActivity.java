package com.qst.giftems.activity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qst.giftems.App;
import com.qst.giftems.R;
import com.qst.giftems.model.User;
import com.qst.giftems.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * 我的资料
 */
public class MyActivity extends BaseActivity {
    static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd",
            Locale.US);

    EditText realNameEditText;
    TextView sexNanTextView, sexNvTextView;
    Button birthdayButton;

    LinearLayout mobileLinearLayout;
    CheckBox mobileCheckBox;
    EditText mobileEditText;
    EditText validateEditText;
    Button validateButton;
    Button okButton;
    TextView messageTextView;

    Button saveButton;

    String sex;

    public MyActivity() {
        super(R.layout.my, "我的资料");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realNameEditText = (EditText) findViewById(R.id.realNameEditText);
        sexNanTextView = (TextView) findViewById(R.id.sexNanTextView);
        sexNvTextView = (TextView) findViewById(R.id.sexNvTextView);
        birthdayButton = (Button) findViewById(R.id.birthdayButton);

        mobileCheckBox = (CheckBox) findViewById(R.id.mobileCheckBox);
        mobileLinearLayout = (LinearLayout) findViewById(R.id.mobileLinearLayout);
        mobileEditText = (EditText) findViewById(R.id.mobileEditText);
        validateEditText = (EditText) findViewById(R.id.validateEditText);
        validateButton = (Button) findViewById(R.id.validateButton);
        okButton = (Button) findViewById(R.id.okButton);
        messageTextView = (TextView) findViewById(R.id.messageTextView);

        saveButton = (Button) findViewById(R.id.saveButton);

        mobileLinearLayout.setVisibility(View.GONE);

        sexNanTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "男";
                sexNanTextView.setBackgroundResource(R.color.title_bottom);
                sexNvTextView.setBackgroundResource(R.drawable.background_border);
            }
        });
        sexNvTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "女";
                sexNvTextView.setBackgroundResource(R.color.title_bottom);
                sexNanTextView.setBackgroundResource(R.drawable.background_border);
            }
        });

        final OnDateSetListener onDateSetListener = new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                    int dayOfMonth) {
                birthdayButton.setText(year + "-" + (monthOfYear + 1) + "-"
                        + dayOfMonth);
            }
        };
        birthdayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = birthdayButton.getText().toString();
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(DATE_FORMAT.parse(date));
                } catch (Exception e) {
                }
                final DatePickerDialog dialog = new DatePickerDialog(
                        MyActivity.this, onDateSetListener, calendar
                                .get(Calendar.YEAR), calendar
                                .get(Calendar.MONTH), calendar
                                .get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        mobileCheckBox
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                            boolean isChecked) {
                        mobileLinearLayout.setVisibility(isChecked ? View.VISIBLE
                                        : View.GONE);
                    }
                });

    }

    void displayUser() {
        App app = (App) getApplication();
        User user = app.user;
        if (user == null)
            return;

        realNameEditText.setText(user.realName != null ? user.realName : "");

        sex = user.sex;
        if (StringUtils.isEmpty(user.sex)) {
            sexNanTextView.setBackgroundResource(R.drawable.background_border);
            sexNvTextView.setBackgroundResource(R.drawable.background_border);
        } else if (user.sex.equals("男")) {
            sexNanTextView.setBackgroundResource(R.color.title_bottom);
            sexNvTextView.setBackgroundResource(R.drawable.background_border);
        } else if (user.sex.equals("女")) {
            sexNvTextView.setBackgroundResource(R.color.title_bottom);
            sexNanTextView.setBackgroundResource(R.drawable.background_border);
        }
        if (user.birthday.length() != 0)
            birthdayButton.setText(user.birthday);
        else
            birthdayButton.setText("设定生日");

        if (user.bindStatus == null || user.bindStatus == 0) {
            mobileCheckBox.setText("绑定手机号码");
            okButton.setText("绑定");
        } else if (user.bindStatus == 1) {
            mobileCheckBox.setText("解除绑定手机号码");
            okButton.setText("解除绑定");
        }
    }
}
