package com.example.tourmate;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.tourmate.ConvertWeather.ConvertWeather;
import com.example.tourmate.Weather.Weather;
import com.example.tourmate.Weather.WeatherMainPojoClass;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
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

public class FiveDaysWeather extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private List<com.example.tourmate.Weather.List> list;
    private WeatherMainPojoClass weatherMainPojoClass;
    private TextView textView, currentTemp, cityNameTV, weatherType, wind, humidity;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private int i;
    private LatLng latLng;
    private double lat, lng, weatherLat, weatherLan;
    private String cityName,currentCityName,stateName,countryName;
    private Geocoder geocoder;
    private List <Address> addresses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_days_weather);


        recyclerView = findViewById(R.id.recyclerViewRV);
        textView = findViewById(R.id.errorTV);
        currentTemp = findViewById(R.id.currentTempTV);
        cityNameTV = findViewById(R.id.cityNameTV);
        weatherType = findViewById(R.id.weatherTypeTV);
        wind = findViewById(R.id.windTV);
        humidity = findViewById(R.id.humidityTV);
        geocoder = new Geocoder(this);


        getWeatherinformation();


    }

    private void getWeatherinformation() {

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


                    try {
                        addresses = geocoder.getFromLocation(lat,lng,1);
                        cityNameTV.setText(addresses.get(0).getLocality());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    currentTemp.setText(String.valueOf(list.get(i).getMain().getTemp()));
                    //cityNameTV.setText(currentCityName);
                    weatherType.setText(String.valueOf(list.get(i).getWeather().get(i).getDescription()));
                    wind.setText(String.valueOf(list.get(i).getWind().getSpeed()));
                    humidity.setText(String.valueOf(list.get(i).getMain().getHumidity()));


                    customAdapter = new CustomAdapter(FiveDaysWeather.this, list);
                    recyclerView.setLayoutManager(new GridLayoutManager(FiveDaysWeather.this, 5));
                    recyclerView.setAdapter(customAdapter);
                }
            }

            @Override
            public void onFailure(Call<WeatherMainPojoClass> call, Throwable t) {
                textView.setText(t.getMessage());

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
    }

}
