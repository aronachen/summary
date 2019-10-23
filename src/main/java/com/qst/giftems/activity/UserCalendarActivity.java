package com.qst.giftems.activity;

import android.content.Context;
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
import com.qst.giftems.activity.KCalendar.OnCalendarClickListener;
import com.qst.giftems.activity.KCalendar.OnCalendarDateChangedListener;
import com.qst.giftems.model.UserCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/**
 * 日程
 */
public class UserCalendarActivity extends BaseActivity {
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.US);
    static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm", Locale.US);
    static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm",
            Locale.US);

    ArrayList<UserCalendar> userCalendars = new ArrayList<UserCalendar>();
    ArrayList<UserCalendar> userCalendarsOfSelectedDate = new ArrayList<UserCalendar>();
    Calendar selectedDate = Calendar.getInstance();

    KCalendar calendarView;
    ListView eventsListView;
    Button addButton;

    EventsAdapter eventsAdapter = new EventsAdapter();

    int currentUserCalendarId;

    public UserCalendarActivity() {
        super(R.layout.user_calendar, "日程记录");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calendarView = findViewById(R.id.calendarView);
        eventsListView = findViewById(R.id.eventsListView);
        addButton = findViewById(R.id.addButton);

        // calendar
        final TextView yearMonthTextView = findViewById(R.id.yearMonthTextView);
        yearMonthTextView.setText(calendarView.getCalendarYear() + "年" + calendarView.getCalendarMonth() + "月");
        int year = selectedDate.get(Calendar.YEAR);
        int month = selectedDate.get(Calendar.MONTH) + 1;
        yearMonthTextView.setText(year + "年" + month + "月");
        calendarView.showCalendar(year, month);
        calendarView.setCalendarDayBgColor(selectedDate.getTime(), R.drawable.calendar_date_focused);
        calendarView.setOnCalendarClickListener(new OnCalendarClickListener() {
            public void onCalendarClick(int row, int col, String date) {
                int y = selectedDate.get(Calendar.YEAR);
                int m = selectedDate.get(Calendar.MONTH) + 1;
                int d = selectedDate.get(Calendar.DATE);
                try {
                    selectedDate.setTime(DATE_FORMAT.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return;
                }
                int year = selectedDate.get(Calendar.YEAR);
                int month = selectedDate.get(Calendar.MONTH) + 1;
                int day = selectedDate.get(Calendar.DATE);

                if (calendarView.getCalendarMonth() - month == 1
                        || calendarView.getCalendarMonth() - month == -11) {
                    calendarView.lastMonth();
                } else if (month - calendarView.getCalendarMonth() == 1
                        || month - calendarView.getCalendarMonth() == -11) {
                    calendarView.nextMonth();
                } else {
                    calendarView.removeAllBgColor();
                    calendarView.setCalendarDayBgColor(date, R.drawable.calendar_date_focused);
                }
                showUserCalendarsOfSelectedDate();
            }
        });
        calendarView
                .setOnCalendarDateChangedListener(new OnCalendarDateChangedListener() {
                    public void onCalendarDateChanged(int year, int month) {
                        yearMonthTextView.setText(year + "年" + month + "月");
                    }
                });
        RelativeLayout preMonth = (RelativeLayout) findViewById(R.id.preMonth);
        preMonth.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                calendarView.lastMonth();
            }

        });
        RelativeLayout nextMonth = (RelativeLayout) findViewById(R.id.nextMonth);
        nextMonth.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                calendarView.nextMonth();
            }
        });

        eventsListView.setAdapter(eventsAdapter);
        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
//                Intent intent = new Intent(getApplicationContext(),
//                        UserCalendarEventActivity.class);
//                intent.putExtra("actionTime",
//                        DATETIME_FORMAT.format(selectedDate.getTime()));
//                startActivity(intent);
            }
        });

        // TODO 后续章节改为从服务器获取数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        Date now = new Date();
        int u = 1;
        for (int i = 0; i < 6; i++) {
            // 模拟一个随机日期
            Date randomDate = new Date(now.getTime()
                    + (int) ((Math.random() - 0.5) * 100 * 24 * 60 * 60 * 1000));
            // 此日期下随机创建1到4个日程
            int random = (int) (Math.random() / 0.3) + 1;
            for (int j = 0; j < random; j++) {
                UserCalendar uc = new UserCalendar();
                uc.id = u++;
                uc.userId = 1;
                uc.title = "日程 " + uc.id;
                uc.actionTime = sdf.format(randomDate);
                userCalendars.add(uc);
            }
        }
        ArrayList<String> clist = new ArrayList<String>();
        for (UserCalendar uc : userCalendars) {
            clist.add(uc.actionTime.substring(0, 10));
        }
        calendarView.addMarks(clist, 0);
        showUserCalendarsOfSelectedDate();
    }

    void showUserCalendarsOfSelectedDate() {
        int year = selectedDate.get(Calendar.YEAR);
        int month = selectedDate.get(Calendar.MONTH) + 1;
        int day = selectedDate.get(Calendar.DATE);
        userCalendarsOfSelectedDate.clear();
        Calendar temp = Calendar.getInstance();
        for (UserCalendar uc : userCalendars) {
            try {
                temp.setTime(DATETIME_FORMAT.parse(uc.actionTime));
                if (temp.get(Calendar.YEAR) == year
                        && temp.get(Calendar.MONTH) + 1 == month
                        && temp.get(Calendar.DATE) == day)
                    userCalendarsOfSelectedDate.add(uc);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        eventsAdapter.notifyDataSetChanged();
    }

    class EventsAdapter extends BaseAdapter {
        public int getCount() {
            return userCalendarsOfSelectedDate.size();
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
            final UserCalendar uc = userCalendarsOfSelectedDate.get(position);
            if (uc == null)
                return null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.user_calendar_item, null);
                convertView.setOnClickListener(userCalendarViewOCL);
            }
            convertView.setTag(uc);
            TextView timeTextView = convertView.findViewById(R.id.timeTextView);
            TextView titleTextView = convertView.findViewById(R.id.titleTextView);
            ImageView deleteImageView = convertView.findViewById(R.id.deleteImageView);
            timeTextView.setText(uc.actionTime.substring(11));
            titleTextView.setText(uc.title);
            deleteImageView.setTag(uc);
            deleteImageView.setOnClickListener(deleteImageViewOCL);
            return convertView;
        }

        OnClickListener deleteImageViewOCL = new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!checkLogin())
                    return;
                Dialogs.showSimpleDialog(UserCalendarActivity.this,
                        "确定删除这条记录吗？", true, new OnOkListener() {
                            @Override
                            public void onOk() {
                                UserCalendar uc = (UserCalendar) v.getTag();
                                if (uc == null)
                                    return;
                                currentUserCalendarId = uc.id;

                                Iterator<UserCalendar> it1 = userCalendarsOfSelectedDate.iterator();
                                while (it1.hasNext()) {
                                    UserCalendar u = it1.next();
                                    if (u.id == currentUserCalendarId) {
                                        it1.remove();
                                        break;
                                    }
                                }
                                eventsAdapter.notifyDataSetChanged();

                                Iterator<UserCalendar> it2 = userCalendars.iterator();
                                while (it2.hasNext()) {
                                    UserCalendar u = it2.next();
                                    if (u.id == currentUserCalendarId) {
                                        it2.remove();
                                        break;
                                    }
                                }
                                calendarView.removeAllMarks();
                                ArrayList<String> clist = new ArrayList<String>();
                                for (UserCalendar u : userCalendars) {
                                    clist.add(u.actionTime.substring(0, 10));
                                }
                                calendarView.addMarks(clist, 0);
                                // TODO 后续章节加上保存到服务器的功能
                            }
                        });
            }
        };
        OnClickListener userCalendarViewOCL = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                UserCalendar uc = (UserCalendar) v.getTag();
                if (uc == null)
                    return;
//                Intent intent = new Intent(getApplicationContext(),
//                        UserCalendarEventActivity.class);
//                intent.putExtra("userCalendarId", uc.id);
//                intent.putExtra("title", uc.title);
//                intent.putExtra("actionTime", uc.actionTime);
//                intent.putExtra("remindTime1", uc.remindTime1);
//                intent.putExtra("remindTime2", uc.remindTime2);
//                intent.putExtra("remindTime3", uc.remindTime3);
//                intent.putExtra("remindTime4", uc.remindTime4);
//                intent.putExtra("remindTime5", uc.remindTime5);
//                startActivity(intent);
            }
        };
    }
}
