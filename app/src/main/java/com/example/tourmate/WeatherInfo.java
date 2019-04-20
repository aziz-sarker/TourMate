package com.example.tourmate;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourmate.ConvertWeather.ConvertWeather;
import com.example.tourmate.Weather.WeatherMainPojoClass;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherInfo extends AppCompatActivity {


    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private List<com.example.tourmate.Weather.List> list;
    private WeatherMainPojoClass weatherMainPojoClass;
    private TextView errorTextView, currentTempTV, cityNameTV, windTV, humidityTV, dateTV;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private int i;
    private LatLng latLng;
    private double lat, lng;
    private String cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);

        /*recyclerView = findViewById(R.id.recyclerViewRV);
        errorTextView = findViewById(R.id.errorTV);
        currentTempTV = findViewById(R.id.currentTempTV);
        cityNameTV = findViewById(R.id.cityNameTV);
        windTV = findViewById(R.id.cityNameTV);
        humidityTV = findViewById(R.id.humidityTV);
        dateTV = findViewById(R.id.dayDateTV);


        getWeatherinformation();*/

    }

   /* private void getWeatherinformation() {

        getMyCurrentLocation();

        Weather_Interface weather_interface = RetrofitClass.getRetrofitInstance().create(Weather_Interface.class);
        String url = String.format("forecast?lat=%f&lon=%f&units=metric&appid=caba92b53d8c2a02f330775bebbdabc8", lat, lng);
        final Call<WeatherMainPojoClass> weatherMainPojoClassCall = weather_interface.getWeatherData(url);

        weatherMainPojoClassCall.enqueue(new Callback<WeatherMainPojoClass>() {
            @Override
            public void onResponse(Call<WeatherMainPojoClass> call, Response<WeatherMainPojoClass> response) {
                if (response.code() == 200) {

                    weatherMainPojoClass = response.body();
                    list = new ArrayList<com.example.tourmate.Weather.List>();
                    list = weatherMainPojoClass.getList();

                    Geocoder geocoder = new Geocoder(WeatherInfo.this, Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(lat, lng, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //cityName = addresses.get(0).getAddressLine(0);
                    String stateName = addresses.get(0).getAddressLine(1);
                    String countryName = addresses.get(0).getAddressLine(2);

                    currentTempTV.setText(String.valueOf(list.get(i).getMain().getTemp()));
                    //cityNameTV.setText(cityName);
                    dateTV.setText(String.valueOf(ConvertWeather.convertDate(list.get(i).getDt())));
                    humidityTV.setText(String.valueOf(list.get(i).getMain().getHumidity()));
                    windTV.setText(String.valueOf(list.get(i).getWind().getSpeed()));


                    customAdapter = new CustomAdapter(WeatherInfo.this, list);
                    recyclerView.setLayoutManager(new GridLayoutManager(WeatherInfo.this, 5));
                    recyclerView.setAdapter(customAdapter);
                }
            }

            @Override
            public void onFailure(Call<WeatherMainPojoClass> call, Throwable t) {
                errorTextView.setText(t.getMessage());

            }
        });
    }

    private void getMyCurrentLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    Location location = task.getResult();
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    latLng = new LatLng(lat, lng);

                }
            }
        });
    }*/

}
