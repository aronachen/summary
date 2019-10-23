package com.qst.giftems.activity;

import android.content.Context;
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

import com.qst.giftems.R;
import com.qst.giftems.model.GiftType;
import com.qst.giftems.model.Strategy;

import java.util.ArrayList;

/**
 * 二级页面，礼品类型和攻略共用一个。
 */
public class ListLevel2Activity extends BaseActivity {

    public static final int TYPE_GIFT = 50;
    public static final int TYPE_STRATEGY = 100;

    private int type;
    private ArrayList list = new ArrayList();

    private GiftTypeListViewAdapter giftTypeListViewAdapter = new GiftTypeListViewAdapter();
    private StrategyListViewAdapter strategyListViewAdapter = new StrategyListViewAdapter();

    private ListView listView;

    public ListLevel2Activity() {
        super(R.layout.list_level2, "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = findViewById(R.id.listView);

        type = getIntent().getIntExtra("type", 0);
        if (type == TYPE_GIFT) {
            titleTextView.setText("礼品中心");
            // TODO 后续章节改为从服务器获取礼品数据
            int[] pics = { R.drawable.gift_type_1, R.drawable.gift_type_2,
                    R.drawable.gift_type_3, R.drawable.gift_type_4,
                    R.drawable.gift_type_5, R.drawable.gift_type_6,
                    R.drawable.gift_type_7 };
            for (int i = 1; i <= 7; i++) {
                GiftType gt = new GiftType();
                gt.id = i;
                gt.name = "礼品类型 " + i;
                gt.pic = pics[i - 1] + "";
                gt.orderNum = i;
                list.add(gt);
            }
            listView.setAdapter(giftTypeListViewAdapter);
        } else if (type == TYPE_STRATEGY) {
            titleTextView.setText("礼品攻略");
            // TODO 后续章节改为从服务器获取礼品攻略数据
            int[] pics = { R.drawable.strategy_1, R.drawable.strategy_2,
                    R.drawable.strategy_3, R.drawable.strategy_4 };
            for (int i = 1; i <= 4; i++) {
                Strategy s = new Strategy();
                s.id = i;
                s.title = "送礼攻略 " + i;
                s.remark = "备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注";
                s.pic1 = pics[i - 1] + "";
                list.add(s);
            }
            listView.setAdapter(strategyListViewAdapter);
        }
    }

    private class GiftTypeListViewAdapter extends BaseAdapter {
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final Context context = getApplicationContext();
            final GiftType giftType = (GiftType) list.get(position);
            if (giftType == null)
                return null;
            if (convertView == null)
                //将布局文件转化为View对象
                convertView = LayoutInflater.from(context).inflate(R.layout.list_level2_item, null);
            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView textView = convertView.findViewById(R.id.textView);
            textView.setText(giftType.name);
            imageView.setImageBitmap(readBitMapFromResource(ListLevel2Activity.this,
                    Integer.parseInt(giftType.pic)));

            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, GiftActivity.class);
                    intent.putExtra("giftTypeId", giftType.id);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

    private class StrategyListViewAdapter extends BaseAdapter {
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final Context context = getApplicationContext();
            final Strategy strategy = (Strategy) list.get(position);
            if (strategy == null)
                return null;
            if (convertView == null)
                convertView = LayoutInflater.from(context).inflate(R.layout.list_level2_item, null);
            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView textView = convertView.findViewById(R.id.textView);
            textView.setText(strategy.title);
            imageView.setImageBitmap(readBitMapFromResource(ListLevel2Activity.this,
                    Integer.parseInt(strategy.pic1)));
            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StrategyActivity.class);
                    intent.putExtra("strategyId", strategy.id);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }
}
