package com.example.weather_forecast;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.net.ConnectivityManagerCompat;

import java.lang.reflect.Field;

public class AutoReceiver extends IntentService {
    private static final String TAG = "AutoReceiver";
    private static final String key = "com.example.weather_forecast.MainActivity";

    private String location;
    private day1Item mDay1Item;

    public static Intent newIntent(Context context) {
        return new Intent(context, AutoReceiver.class);
    }

    public AutoReceiver() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "Received an intent:" + intent);
    }
    private boolean inNetworkAvailableAndConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = cm.getActiveNetworkInfo()!=null;
        boolean isNetworkConnected = isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }
}