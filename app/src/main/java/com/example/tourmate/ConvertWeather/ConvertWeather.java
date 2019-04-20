package com.example.tourmate.ConvertWeather;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConvertWeather {

    public static final String APP_ID ="caba92b53d8c2a02f330775bebbdabc8";

    public static String convertUnixToDate (long wDate){

        Date date = new Date(wDate*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm ");
        String format = sdf.format(date);
        return format;
    }

    public static String convertDate (long wDate){

        Date date = new Date(wDate*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("E MM yyyy");
        String format = sdf.format(date);
        return format;
    }

    public static String convertUnixToHour (long wDate){

        Date date = new Date(wDate*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String format = sdf.format(date);
        return format;
    }

    private String getTodayDateInStringFormat() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("E, d MMMM", Locale.getDefault());
        return df.format(c.getTime());
    }


}
