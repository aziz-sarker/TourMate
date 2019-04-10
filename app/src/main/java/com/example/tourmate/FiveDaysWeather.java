package com.example.tourmate;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourmate.Weather.Weather;
import com.example.tourmate.Weather.WeatherMainPojoClass;
import com.example.tourmate.databinding.ActivityFiveDaysWeatherBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiveDaysWeather extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private List<com.example.tourmate.Weather.List> list;
    private WeatherMainPojoClass weatherMainPojoClass;
    private  TextView textView;
    private ActivityFiveDaysWeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContentView(this,R.layout.activity_five_days_weather);
        recyclerView = findViewById(R.id.recyclerViewRV);
        //textView = findViewById(R.id.errorTV);

        getWeatherinformation();
    }

    private void getWeatherinformation() {
        Weather_Interface weather_interface = RetrofitClass.getRetrofitInstance().create(Weather_Interface.class);
        String url = String.format("forecast?lat=23.8627903&lon=90.4221042&units=metric&appid=caba92b53d8c2a02f330775bebbdabc8");
        Call<WeatherMainPojoClass> weatherMainPojoClassCall = weather_interface.getWeatherData(url);

       weatherMainPojoClassCall.enqueue(new Callback<WeatherMainPojoClass>() {
           @Override
           public void onResponse(Call<WeatherMainPojoClass> call, Response<WeatherMainPojoClass> response) {
               if (response.code()==200){

                   weatherMainPojoClass = response.body();
                   list = new ArrayList<>();
                   list =weatherMainPojoClass.getList();
                   customAdapter = new CustomAdapter(FiveDaysWeather.this,list);
                   recyclerView.setLayoutManager(new LinearLayoutManager(FiveDaysWeather.this));
                   recyclerView.setAdapter(customAdapter);
               }
           }

           @Override
           public void onFailure(Call<WeatherMainPojoClass> call, Throwable t) {
               //textView.setText(t.getMessage());
               binding.errorTV.setText(t.getMessage());

           }
       });
    }
}
