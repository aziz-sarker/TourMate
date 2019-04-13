package com.example.tourmate.ConvertWeather;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertWeather {

    public static final String APP_ID ="caba92b53d8c2a02f330775bebbdabc8";

    public static String convertUnixToDate (long wDate){

        Date date = new Date(wDate*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm EEE");
        String format = sdf.format(date);
        return format;
    }

    public static String convertUnixToHour (long wDate){

        Date date = new Date(wDate*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String format = sdf.format(date);
        return format;
    }

    public static String convertDateToDayMonth (long date){

        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        String day          = (String) DateFormat.format("dd",   date); // 20
        String monthString  = (String) DateFormat.format("MMM",  date); // Jun
        String monthNumber  = (String) DateFormat.format("MM",   date); // 06
        String year         = (String) DateFormat.format("yyyy", date); // 2013
        String displayDayMonth = dayOfTheWeek+", "+day+"."+monthNumber+"."+year;
        return displayDayMonth;
    }




}
