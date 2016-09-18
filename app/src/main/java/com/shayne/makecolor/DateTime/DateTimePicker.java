package com.shayne.makecolor.DateTime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.shayne.makecolor.R;

import java.util.Calendar;

public class DateTimePicker extends AppCompatActivity {


    private DatePicker  datePicker;
    private TimePicker  timePicker;
    private Calendar  calendar;
    private int year;
    private int mouth;
    private int day;
    private int hour;
    private  int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
          //  获取日历的一个对象
        calendar  = Calendar.getInstance();
        //   获取年月日时分秒的信息
        year = calendar.get(Calendar.YEAR);
        mouth = calendar.get(Calendar.MONTH)+1;   // MOUITH是从0开始计数的
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        setTitle(year+"--"+mouth+"--"+day+"--"+hour+"--"+mouth);



    }


}
