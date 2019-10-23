package com.qst.giftems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qst.giftems.Dialogs;
import com.qst.giftems.Dialogs.OnOkListener;
import com.qst.giftems.R;
import com.qst.giftems.model.UserAddress;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 收礼人列表
 */
public class UserAddressListActivity extends BaseActivity {

    public static final int SELECT_USER_ADDRESS_RESULT_CODE = 10;

    ArrayList<UserAddress> userAddresses = new ArrayList<UserAddress>();
    int currentUserAddressId;

    UserAddress selectedUserAddress;
    View selectedUserAddressItemView;

    ListView listView;
    TextView nodataTextView;
    RelativeLayout bottomRelativeLayout;
    Button okButton;

    public UserAddressListActivity() {
        super(R.layout.list_user_address, "已登记收礼人");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = findViewById(R.id.listView);
        nodataTextView = findViewById(R.id.nodataTextView);
        okButton = findViewById(R.id.okButton);
        nodataTextView.setVisibility(View.GONE);

        if (getCallingActivity() != null) {
            okButton.setText("确定选择收礼人");
            okButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedUserAddress == null) {
                        showMessage("请选择收礼人");
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("userAddressId", selectedUserAddress.id);
                    intent.putExtra("name", selectedUserAddress.name);
                    intent.putExtra("mobile", selectedUserAddress.mobile);
                    intent.putExtra("provinceId", selectedUserAddress.provinceId);
                    intent.putExtra("cityId", selectedUserAddress.cityId);
                    intent.putExtra("areaId", selectedUserAddress.areaId);
                    intent.putExtra("address", selectedUserAddress.address);
                    setResult(SELECT_USER_ADDRESS_RESULT_CODE, intent);
                    finish();
                }
            });
        } else {
            okButton.setText("登记新收礼人");
            okButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),
                            UserAddressActivity.class);
                    startActivity(intent);
                }
            });
        }

        listView.setAdapter(new UserAddressListViewAdapter());

        // TODO 后续章节改为从服务器获取数据
        for (int i = 1; i <= 10; i++) {
            UserAddress ua = new UserAddress();
            ua.id = i;
            ua.userId = 1;
            ua.name = "姓名 " + i;
            ua.mobile = "1234567890" + i;
            ua.provinceId = 1;
            ua.cityId = 1;
            ua.areaId = 1;
            ua.address = "地址地址地址地址地址地址地址地址地址地址地址" + i;
            ua.postcode = "123456";
            ua.info = "";
            ua.provinceName = "北京市";
            ua.cityName = "北京市";
            ua.areaName = "东城区";
            userAddresses.add(ua);
        }
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

    private class UserAddressListViewAdapter extends BaseAdapter {
        public int getCount() {
            return userAddresses.size();
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        public Object getItem(int position) {
            return userAddresses.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            UserAddress userAddress = userAddresses.get(position);
            if (userAddress == null)
                return null;
            if (convertView == null) {
                convertView = LayoutInflater
                        .from(getApplicationContext())
                        .inflate(R.layout.list_user_address_item, parent, false);
            }
            convertView.setTag(userAddress);
            TextView nameTextView = convertView.findViewById(R.id.nameTextView);
            TextView mobileTextView = convertView.findViewById(R.id.mobileTextView);
            TextView pcaTextView = convertView.findViewById(R.id.pcaTextView);
            TextView addressTextView = convertView.findViewById(R.id.addressTextView);
            ImageView deleteImageView = convertView.findViewById(R.id.deleteImageView);

            nameTextView.setText(userAddress.name);
            mobileTextView.setText(userAddress.mobile);
            pcaTextView.setText(userAddress.provinceName + " "
                    + userAddress.cityName + " " + userAddress.areaName);
            addressTextView.setText(userAddress.address);
            deleteImageView.setTag(userAddress);

            if (getCallingActivity() != null) {
                deleteImageView.setVisibility(View.GONE);
                convertView.setOnClickListener(onClickListener1);
            } else {
                deleteImageView.setVisibility(View.VISIBLE);
                deleteImageView.setOnClickListener(deleteOCL);
                convertView.setOnClickListener(onClickListener2);
            }

            convertView.setTag(userAddress);

            return convertView;
        }

        OnClickListener onClickListener1 = new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView selectedImageView = v.findViewById(R.id.selectedImageView);
                UserAddress item = (UserAddress) v.getTag();
                if (selectedImageView.getVisibility() == View.GONE) {
                    if (selectedUserAddress != null) {
                        selectedUserAddressItemView.findViewById(
                                R.id.selectedImageView)
                                .setVisibility(View.GONE);
                    }
                    selectedImageView.setVisibility(View.VISIBLE);
                    selectedUserAddress = item;
                    selectedUserAddressItemView = v;
                } else {
                    selectedImageView.setVisibility(View.GONE);
                    selectedUserAddress = null;
                    selectedUserAddressItemView = null;
                }
            }
        };

        OnClickListener onClickListener2 = new OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAddress item = (UserAddress) v.getTag();

                Intent intent = new Intent(getApplicationContext(),
                        UserAddressActivity.class);
                intent.putExtra("userAddressId", item.id);
                intent.putExtra("name", item.name);
                intent.putExtra("mobile", item.mobile);
                intent.putExtra("provinceId", item.provinceId);
                intent.putExtra("cityId", item.cityId);
                intent.putExtra("areaId", item.areaId);
                intent.putExtra("address", item.address);
                startActivity(intent);
            }
        };
        OnClickListener deleteOCL = new OnClickListener() {
            @Override
            public void onClick(View v) {
                final int userAddressId = ((UserAddress) v.getTag()).id;
                Dialogs.showSimpleDialog(UserAddressListActivity.this,
                        "确定删除这条收礼人信息？", true, new OnOkListener() {
                            @Override
                            public void onOk() {
                                currentUserAddressId = userAddressId;
                                // TODO 后续章节改为从服务器删除数据
                                Iterator<UserAddress> it = userAddresses.iterator();
                                while (it.hasNext()) {
                                    UserAddress item = it.next();
                                    if (item.id == currentUserAddressId)
                                        it.remove();
                                }
                                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                            }
                        });
            }
        };
    }

}
