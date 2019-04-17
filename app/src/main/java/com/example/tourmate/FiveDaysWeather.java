package com.example.tourmate;

import android.databinding.DataBindingUtil;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourmate.ConvertWeather.ConvertWeather;
import com.example.tourmate.Weather.Weather;
import com.example.tourmate.Weather.WeatherMainPojoClass;
import com.example.tourmate.databinding.ActivityFiveDaysWeatherBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiveDaysWeather extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private List<com.example.tourmate.Weather.List> list;
    private WeatherMainPojoClass weatherMainPojoClass;
    private TextView textView;
    private ActivityFiveDaysWeatherBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LatLng latLng;
    private double lat,lng;
    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:SSSS", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_five_days_weather);
        recyclerView = findViewById(R.id.recyclerViewRV);
        //textView = findViewById(R.id.errorTV);

        getWeatherinformation();
    }

    private void getWeatherinformation() {

        getMyCurrentLocation();

        Weather_Interface weather_interface = RetrofitClass.getRetrofitInstance().create(Weather_Interface.class);
        String url = String.format("forecast?lat=%f&lon=%f&units=metric&appid=caba92b53d8c2a02f330775bebbdabc8",lat,lng);
        Call<WeatherMainPojoClass> weatherMainPojoClassCall = weather_interface.getWeatherData(url);

        weatherMainPojoClassCall.enqueue(new Callback<WeatherMainPojoClass>() {
            @Override
            public void onResponse(Call<WeatherMainPojoClass> call, Response<WeatherMainPojoClass> response) {
                if (response.code() == 200) {

                    weatherMainPojoClass = response.body();
                    list = new ArrayList<com.example.tourmate.Weather.List>();
                    list = weatherMainPojoClass.getList();

                    int[] everyday = new int[]{0,0,0,0,0,0,0};

                    for (int i=0; i<list.size();i++){

                        long date = list.get(i).getDt();
                        //String dayOfWeek = convertDateToDay(date);
                        double tempMin = list.get(i).getMain().getTempMin();
                        double tempMax = list.get(i).getMain().getTempMax();

                        if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Sat") && everyday[0] < 1){
                            list.add(new com.example.tourmate.Weather.List(date,tempMin,tempMax));
                            everyday[0] = 1;
                        }if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Sun") && everyday[1] < 1){
                            list.add(new com.example.tourmate.Weather.List(date,tempMin,tempMax));
                            everyday[1] = 1;

                        } if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Mon") && everyday[2] < 1){
                            list.add(new com.example.tourmate.Weather.List(date,tempMin,tempMax));
                            everyday[2] = 1;

                        } if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Tue") && everyday[3] < 1){
                            list.add(new com.example.tourmate.Weather.List(date,tempMin,tempMax));
                            everyday[3] = 1;

                        } if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Wed") && everyday[4] < 1){
                            list.add(new com.example.tourmate.Weather.List(date,tempMin,tempMax));
                            everyday[4] = 1;

                        }if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Thu") && everyday[5] < 1){
                            list.add(new com.example.tourmate.Weather.List(date,tempMin,tempMax));
                            everyday[5] = 1;

                        } if (String.valueOf(ConvertWeather.convertUnixToDate(date)).equals("Fri") && everyday[6] < 1){
                            list.add(new com.example.tourmate.Weather.List(date,tempMin,tempMax));
                            everyday[6] = 1;

                        }
                    }

                    customAdapter = new CustomAdapter(FiveDaysWeather.this, list);
                    recyclerView.setLayoutManager(new GridLayoutManager(FiveDaysWeather.this,5));
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

    private void getMyCurrentLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()){
                    Location location = task.getResult();
                     lat = location.getLatitude();
                     lng = location.getLongitude();
                    latLng = new LatLng(lat,lng);

                }
            }
        });
    }

   /* private String convertDateToDay(String time) {
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
