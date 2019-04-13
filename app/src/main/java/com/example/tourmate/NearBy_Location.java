package com.example.tourmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tourmate.NearbyLocation.NearbyLocation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearBy_Location extends AppCompatActivity {

    private TextView textView;
    private NearbyLocation nearbyLocation;
    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_location);
        textView = findViewById(R.id.errorLocationTV);
        getLocationData();
    }

    private void getLocationData() {

       Location_Interface location_interface = RetrofitClass.getRetrofitLocationInstance().create(Location_Interface.class);
       String url = String.format("json?location=-33.8670522,151.1957362&radius=1500&type=bank&key=AIzaSyAGKJfnsFM5lV-LP0KTw5RhjlRbNqwi2Pc");
       final Call<NearbyLocation> nearbyLocationCall = location_interface.getLocationData(url);

       nearbyLocationCall.enqueue(new Callback<NearbyLocation>() {
           @Override
           public void onResponse(Call<NearbyLocation> call, Response<NearbyLocation> response) {

               if (response.code() ==200){
                   nearbyLocation =response.body();

                   list = new ArrayList();
                   list = nearbyLocation.getResults();
               }

           }

           @Override
           public void onFailure(Call<NearbyLocation> call, Throwable t) {

               textView.setText(t.getMessage());

           }
       });

    }

}
