package com.qst.giftems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.qst.giftems.Dialogs;
import com.qst.giftems.Dialogs.OnOkListener;
import com.qst.giftems.R;
import com.qst.giftems.model.Gift;
import com.qst.giftems.model.GiftStyle;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 我的收藏
 */
public class FavoriteActivity extends BaseActivity {
    ArrayList<Gift> gifts = new ArrayList<Gift>();

    Gift selectedGift;
    View selectedGiftItemView;

    int currentGiftId;

    TextView nodataTextView;
    ListView listView;

    public FavoriteActivity() {
        super(R.layout.list_favorite, "我的收藏");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nodataTextView = findViewById(R.id.nodataTextView);
        listView = findViewById(R.id.listView);
        listView.setAdapter(new GiftListViewAdapter());
        nodataTextView.setVisibility(View.GONE);

        // TODO 后续章节改为从服务器获取数据
        for (int i = 1; i <= 10; i++) {
            Gift gift = new Gift();
            gift.id = i;
            gift.name = "测试礼品 " + i;
            gift.remark = "礼品介绍礼品介绍";
            gift.likes = 10;
            gift.sales = 100;
            gift.collected = false;
            int[] pics = { R.drawable.gift_1, R.drawable.gift_2, R.drawable.gift_3 };
            for (int j = 1; j <= 3; j++) {
                GiftStyle gs = new GiftStyle();
                gs.id = j;
                gs.name = "款式 " + j;
                gs.price = 199;
                gs.discount = 169;
                gs.remark = "款式介绍";
                gs.pic1 = pics[j - 1] + "";
                gs.pic2 = R.drawable.gift_0 + "";
                gs.pic3 = R.drawable.gift_0 + "";
                gs.orderNumber = i;
                gs.gift = gift;
                gift.styles.add(gs);
            }
            gifts.add(gift);
        }
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

    private class GiftListViewAdapter extends BaseAdapter {
        public int getCount() {
            return gifts.size();
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        public Object getItem(int position) {
            return gifts.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Gift gift = gifts.get(position);
            if (gift == null)
                return null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.list_favorite_item, parent, false);
                convertView.setOnClickListener(onClickListener);
            }
            convertView.setTag(gift);

            ImageView stylePicImageView = convertView.findViewById(R.id.stylePicImageView);
            TextView giftNameTextView = convertView.findViewById(R.id.giftNameTextView);
            ImageView deleteImageView = convertView.findViewById(R.id.deleteImageView);
            TextView priceTextView = convertView.findViewById(R.id.priceTextView);

            stylePicImageView.setImageBitmap(readBitMapFromResource(
                    FavoriteActivity.this,
                    Integer.parseInt(gift.styles.get(0).pic1)));
            giftNameTextView.setText(gift.name);
            priceTextView.setText(gift.styles.get(0).discount + "");

            deleteImageView.setTag(gift);
            deleteImageView.setOnClickListener(deleteOCL);

            return convertView;
        }

        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Gift gift = (Gift) v.getTag();
                Intent intent = new Intent(getApplicationContext(), GiftActivity.class);
                intent.putExtra("giftId", gift.id);
                startActivity(intent);
            }
        };
        OnClickListener deleteOCL = new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Gift gift = (Gift) v.getTag();
                Dialogs.showSimpleDialog(FavoriteActivity.this,
                        "您确定不再收藏这个礼品吗？", true, new OnOkListener() {
                            @Override
                            public void onOk() {
                                currentGiftId = gift.id;
                                Iterator<Gift> it = gifts.iterator();
                                while (it.hasNext()) {
                                    Gift item = it.next();
                                    if (item.id == currentGiftId)
                                        it.remove();
                                }
                                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                            }
                        });
            }
        };
    }

}
