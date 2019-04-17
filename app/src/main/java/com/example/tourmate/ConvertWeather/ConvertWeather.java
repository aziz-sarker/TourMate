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
        SimpleDateFormat sdf = new SimpleDateFormat(" E ");
        String format = sdf.format(date);
        return format;
    }

    public static String convertUnixToHour (long wDate){

        Date date = new Date(wDate*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String format = sdf.format(date);
        return format;
    }

    /*public static String convertDateToDay(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SSSS", Locale.getDefault());
        String days = "";
        try {
            Date date = format.parse(time);
            System.out.println("Our time " + date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            days = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
            System.out.println("Our time " + days);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }*/




}
