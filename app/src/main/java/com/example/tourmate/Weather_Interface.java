package com.example.tourmate;

import com.example.tourmate.Weather.WeatherMainPojoClass;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Weather_Interface {
    @GET
    Call<WeatherMainPojoClass> getWeatherData(@Url String url);
}
