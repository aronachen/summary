package com.qst.giftems;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.qst.giftems.activity.ListLevel2Activity;
import com.qst.giftems.activity.LoginActivity;
import com.qst.giftems.activity.PersonalActivity;
import com.qst.giftems.activity.SettingActivity;
import com.qst.giftems.activity.UserCalendarActivity;

public class MainActivity extends AppCompatActivity {


    private TextView titleTextView; // 标题
    private ViewPager viewPager;    // ViewPager

    // 右下角扇形菜单
    protected RelativeLayout cornerMenuOpenRelativeLayout;
    protected ImageView settingImageView; // 设置
    protected ImageView calendarImageView; // 日历
    protected ImageView personalImageView; // 个人中心
    protected ImageView shoppingBagImageView; // 购物袋
    protected ImageView showCornerMenuImageView; // 展开/收缩

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        titleTextView = findViewById(R.id.titleTextView);
        viewPager = findViewById(R.id.viewPager);

        cornerMenuOpenRelativeLayout = findViewById(R.id.cornerMenuOpenRelativeLayout);
        settingImageView = findViewById(R.id.settingImageView);
        calendarImageView =  findViewById(R.id.calendarImageView);
        personalImageView =  findViewById(R.id.personalImageView);
        shoppingBagImageView = findViewById(R.id.shoppingBagImageView);
        showCornerMenuImageView =  findViewById(R.id.showCornerMenuImageView);

        titleTextView.setText("礼物推荐");

        init();
    }

    /**
     * 只是检查是否保存了mobileToken，没有到服务器验证
     */
    boolean checkLogin() {
        App app = (App) getApplication();
        if (app.user == null || app.user.mobileToken == null) {
            Dialogs.showSimpleDialog(this, "登录后才能继续操作，您现在登录吗？",
                    true, new Dialogs.OnOkListener() {
                        @Override
                        public void onOk() {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    });
            return false;
        }
        return true;
    }

    private void init() {       //addOnPageChangeListener   //setOnPageChangeListener
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            //此方法是页面跳转完后得到调用，index是当前选中页面的Position
            public void onPageSelected(int index) {
                switch (index) {
                    case 0:
                        titleTextView.setText("礼物推荐");
                        break;
                    case 1:
                        titleTextView.setText("礼品中心");
                        break;
                    case 2:
                        titleTextView.setText("礼品攻略");
                        break;
                }
            }

            @Override
            //当页面滑动的时候会调用此方法，在滑动被停止前，此方法会一直得到调用
            //arg0，当前页面；arg1，当前页面偏移的百分比；arg2，当前页面偏移的像素位置；
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            //状态改变的时候调用，三种状态（0，1，2）；0，什么都不做；1，正在滑动；2，滑动完成
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        viewPager.setAdapter(new PagerAdapter() {

            @Override
            //返回viewpager页面的个数
            public int getCount() {
                return 3;
            }

            @Override
            //判断显示的是否是同一张图片，将两个参数相比较返回。
            public boolean isViewFromObject(View v, Object obj) {
                return v == obj;
            }

            @Override
            //View -> ViewGroup
            //为给定的位置移除相应的View；
            //container，ViewPager本身；position，给定的位置；object，在instantiateItem中提交给ViewPager进行保存的对象
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            //View -> ViewGroup
            //为给定位置创建相应的View，创建View之后，需要在该方法中自行添加到container
            //container，ViewPager本身；position，给定的位置；return，提交给ViewPager进行保存的实例对象
            public Object instantiateItem(ViewGroup container, int position) {
                final Context context = getApplicationContext();
                View v = null;
                if (position == 0) {
                    v = getLayoutInflater().inflate(R.layout.index_recommend, viewPager, false);
                    ViewFlipper vf = v.findViewById(R.id.topViewFlipper);
                    vf.setInAnimation(context, R.anim.in_from_right);
                    vf.setOutAnimation(context, R.anim.out_to_left);

                    ViewFlipper.LayoutParams params = new ViewFlipper.LayoutParams(
                            ViewFlipper.LayoutParams.MATCH_PARENT,
                            ViewFlipper.LayoutParams.MATCH_PARENT);

                    ImageView iv1 = new ImageView(context);
                    iv1.setScaleType(ScaleType.CENTER_CROP);
                    iv1.setImageResource(R.drawable.index_recommend_top1);
                    vf.addView(iv1, params);

                    ImageView iv2 = new ImageView(context);
                    iv2.setScaleType(ScaleType.CENTER_CROP);
                    iv2.setImageResource(R.drawable.index_recommend_top2);
                    vf.addView(iv2, params);

                    ImageView iv3 = new ImageView(context);
                    iv3.setScaleType(ScaleType.CENTER_CROP);
                    iv3.setImageResource(R.drawable.index_recommend_top3);
                    vf.addView(iv3, params);

                    ImageView bottomLeft = v.findViewById(R.id.bottomLeftImageView);
                    bottomLeft.setImageResource(R.drawable.index_recommend_leftbottom);

                    ImageView bottomRight = v.findViewById(R.id.bottomRightImageView);
                    bottomRight.setImageResource(R.drawable.index_recommend_rightbottom);
                } else if (position == 1) {
                    v = new ImageView(context);
                    ImageView iv = (ImageView) v;
                    iv.setScaleType(ScaleType.CENTER_CROP);
                    iv.setImageResource(R.drawable.index_giftcenter);

                    iv.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ListLevel2Activity.class);
                            intent.putExtra("type", ListLevel2Activity.TYPE_GIFT);
                            startActivity(intent);
                        }
                    });
                } else if (position == 2) {
                    v = new ImageView(context);
                    ImageView iv = (ImageView) v;
                    iv.setScaleType(ScaleType.CENTER_CROP);
                    iv.setImageResource(R.drawable.index_strategy);

                    iv.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ListLevel2Activity.class);
                            intent.putExtra("type", ListLevel2Activity.TYPE_STRATEGY);
                            startActivity(intent);
                        }
                    });
                }

                final ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                container.addView(v, p);
                return v;
            }
        });

        settingImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        calendarImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 后续章节添加功能
                if (!checkLogin())
                    return;
                Intent intent = new Intent(getApplicationContext(), UserCalendarActivity.class);
                startActivity(intent);
            }
        });
        shoppingBagImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 后续章节添加功能
            }
        });
        personalImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                Intent intent = new Intent(getApplicationContext(), PersonalActivity.class);
                startActivity(intent);
            }
        });
        cornerMenuOpenRelativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerMenuOpenRelativeLayout.setVisibility(View.GONE);
                showCornerMenuImageView.setVisibility(View.VISIBLE);
            }
        });
        showCornerMenuImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerMenuOpenRelativeLayout.setVisibility(View.VISIBLE);
                showCornerMenuImageView.setVisibility(View.GONE);
            }
        });
    }

}
