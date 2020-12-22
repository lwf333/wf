package com.example.weather_forecast;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class detail_activity extends AppCompatActivity {
    private static final String EXTRA_ITEM = "com.example.weather_forecast.item";
    private Intent_GalleryItem mIntent_galleryItem;
    private TextView mweek;
    private TextView mdate;
    private TextView mmax;
    private TextView mmin;
    private TextView mweather;
    private TextView mhuidity;
    private TextView mpressure;
    private TextView mwind;
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item_view);
        final Data app = (Data)getApplication();
        String mTemperature_unit = app.getTemperature_unit();

        mIntent_galleryItem = getIntent().getParcelableExtra(EXTRA_ITEM);
        mweek = (TextView)findViewById(R.id.detail_week);
        mdate = (TextView)findViewById(R.id.detail_date);
        mmax = (TextView)findViewById(R.id.detail_max);
        mmin = (TextView)findViewById(R.id.detail_min);
        mweather = (TextView)findViewById(R.id.detail_weather);
        mhuidity = (TextView)findViewById(R.id.detail_humidity);
        mpressure = (TextView)findViewById(R.id.detail_pressure);
        mwind = (TextView)findViewById(R.id.detail_wind);
        mImageView = (ImageView) findViewById(R.id.detail_image);

        String date = null;
        String weekday = null;
        String time = null;
        date = mIntent_galleryItem.getFxDate();
        try {
            if (Time.IsToday(date)){
                weekday = "Today";
            } else if (Time.IsTomorrow(date)){
                weekday = "Tomorrow";
            } else {
                weekday = Time.StringToWeek(date);
            }
            time = Time.DateChange(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mweek.setText(weekday);
        mdate.setText(time);

        String max;
        String min;
        if (mTemperature_unit.equals("C")){
            max =  mIntent_galleryItem.getTempMax()+"℃";
            min =  mIntent_galleryItem.getTempMin()+"℃";
        } else {
            max = (int) (Integer.parseInt( mIntent_galleryItem.getTempMax()) * 1.8 + 32) +"℉";
            min = (int) (Integer.parseInt( mIntent_galleryItem.getTempMin()) * 1.8 + 32) +"℉";
        }

        mmax.setText(max);
        mmin.setText(min);
        mweather.setText(mIntent_galleryItem.getTextDay());
        mhuidity.setText("Humidity: "+mIntent_galleryItem.getHumidity()+" %");
        mpressure.setText("Pressure: "+mIntent_galleryItem.getPressure()+" hPa");
        mwind.setText("Wind: "+mIntent_galleryItem.getWindspeedday()+" km/h  "+mIntent_galleryItem.getWinddirday());

        String Icon = mIntent_galleryItem.getIconDay();
        String name = "s2"+Icon;
        Class drawable = R.drawable.class;
        int resId;
        try {
            Field field = drawable.getField(name);
            resId = field.getInt(name);
        }catch (NoSuchFieldException e){
            resId = 0;
        }catch (IllegalAccessException e){
            resId = 0;
        }
        Drawable mdrawable  = getResources().getDrawable(resId);

        mImageView.setImageDrawable(mdrawable);
    }
}
