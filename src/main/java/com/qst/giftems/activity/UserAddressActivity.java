package com.qst.giftems.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.qst.giftems.R;
import com.qst.giftems.model.UserAddress;
import com.qst.giftems.util.Areas;
import com.qst.giftems.util.StringUtils;

/**
 * 登记收礼人
 */
public class UserAddressActivity extends BaseActivity {

    UserAddress userAddress = new UserAddress();

    EditText nameEditText;
    EditText mobileEditText;
    Spinner provinceSpinner;
    Spinner citySpinner;
    Spinner areaSpinner;
    EditText addressEditText;

    Button okButton;

    public UserAddressActivity() {
        super(R.layout.user_address, "我的收礼人");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameEditText = findViewById(R.id.nameEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        provinceSpinner = findViewById(R.id.provinceSpinner);
        citySpinner = findViewById(R.id.citySpinner);
        areaSpinner = findViewById(R.id.areaSpinner);
        addressEditText = findViewById(R.id.addressEditText);
        okButton = findViewById(R.id.okButton);

        ArrayAdapter<Areas.Province> paa = new ArrayAdapter<Areas.Province>(
                getApplicationContext(), R.layout.spinner_item, Areas.provinces);
        provinceSpinner.setAdapter(paa);

        int userAddressId = getIntent().getIntExtra("userAddressId", 0);
        userAddress.id = userAddressId;
        if (userAddressId == 0) {
            titleTextView.setText("登记新收礼人");
        } else {
            titleTextView.setText("修改收礼人信息");

            userAddress.name = getIntent().getStringExtra("name");
            userAddress.mobile = getIntent().getStringExtra("mobile");
            userAddress.provinceId = getIntent().getIntExtra("provinceId", 0);
            userAddress.cityId = getIntent().getIntExtra("cityId", 0);
            userAddress.areaId = getIntent().getIntExtra("areaId", 0);
            userAddress.address = getIntent().getStringExtra("address");

            nameEditText.setText(userAddress.name);
            mobileEditText.setText(userAddress.mobile);
            addressEditText.setText(userAddress.address);

            // 设置省市区下拉菜单选中收礼人的省市区
            int pp = 0, cc = 0, aa = 0;
            if (userAddress.provinceId != 0) {
                for (int i = 0; i < provinceSpinner.getCount(); i++) {
                    Areas.Province p = Areas.provinces.get(i);
                    if (p.id == userAddress.provinceId) {
                        // provinceSpinner.setSelection(i);
                        pp = i;
                        ArrayAdapter<Areas.City> caa = new ArrayAdapter<Areas.City>(
                                getApplicationContext(), R.layout.spinner_item,
                                p.cities);
                        citySpinner.setAdapter(caa);
                        if (userAddress.cityId != 0) {
                            for (int j = 0; j < citySpinner.getCount(); j++) {
                                Areas.City c = p.cities.get(j);
                                if (c.id == userAddress.cityId) {
                                    // citySpinner.setSelection(j);
                                    cc = j;
                                    ArrayAdapter<Areas.Area> aaa = new ArrayAdapter<Areas.Area>(
                                            getApplicationContext(),
                                            R.layout.spinner_item, c.areas);
                                    areaSpinner.setAdapter(aaa);
                                    if (userAddress.areaId != 0) {
                                        for (int k = 0; k < areaSpinner.getCount(); k++) {
                                            Areas.Area a = c.areas.get(k);
                                            if (a.id == userAddress.areaId) {
                                                // areaSpinner.setSelection(k);
                                                aa = k;
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }
            provinceSpinner.setSelection(pp);
            citySpinner.setSelection(cc);
            areaSpinner.setSelection(aa);
        }

        provinceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                Areas.Province p = (Areas.Province) provinceSpinner.getSelectedItem();
                if (p.id == userAddress.provinceId)
                    return;
                ArrayAdapter<Areas.City> caa = new ArrayAdapter<Areas.City>(
                        getApplicationContext(),
                        R.layout.spinner_item,
                        ((Areas.Province) provinceSpinner.getSelectedItem()).cities);
                citySpinner.setAdapter(caa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                Areas.City c = (Areas.City) citySpinner.getSelectedItem();
                if (c.id == userAddress.cityId)
                    return;
                ArrayAdapter<Areas.Area> aaa = new ArrayAdapter<Areas.Area>(
                        getApplicationContext(), R.layout.spinner_item,
                        ((Areas.City) citySpinner.getSelectedItem()).areas);
                areaSpinner.setAdapter(aaa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        okButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;

                String name = nameEditText.getText().toString();
                if (StringUtils.isEmpty(name)) {
                    showMessage("请输入收礼人姓名");
                    return;
                }
                String mobile = mobileEditText.getText().toString();
                if (StringUtils.isEmpty(mobile)) {
                    showMessage("请输入收礼人手机号码");
                    return;
                }
                int provinceId = 0;
                int cityId = 0;
                int areaId = 0;
                Areas.Province province = (Areas.Province) provinceSpinner.getSelectedItem();
                if (province != null) {
                    provinceId = province.id;
                    Areas.City city = (Areas.City) citySpinner.getSelectedItem();
                    if (city != null) {
                        cityId = city.id;
                        Areas.Area area = (Areas.Area) areaSpinner.getSelectedItem();
                        if (area != null)
                            areaId = area.id;
                    }
                }
                String address = addressEditText.getText().toString();

                userAddress.name = name;
                userAddress.mobile = mobile;
                userAddress.provinceId = provinceId;
                userAddress.cityId = cityId;
                userAddress.areaId = areaId;
                userAddress.address = address;
                // TODO 后续章节完成保存数据到服务器的功能
            }
        });
    }
}
