package com.example.tourmate;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.tourmate.NearbyLocation.NearbyLocation;
import com.example.tourmate.NearbyLocation.Result;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearBy_Location extends AppCompatActivity implements LocationListener {

    private TextView textView;
    private NearbyLocation nearbyLocation;
    private LocationManager locationManager;
    private List <Result> list;
    private RecyclerView recyclerView;
    private NearbyAdapter nearbyAdapter;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private double lat,lng;
    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_location);
        textView = findViewById(R.id.errorLocationTV);
        recyclerView = findViewById(R.id.nearbyRecyclerViewNRV);
        getMyCurrentLocation();
        getNearbyLocationData();
    }

    private void getNearbyLocationData() {

        /*23.7526973,90.3927751*/

       Location_Interface location_interface = RetrofitClass.getRetrofitLocationInstance().create(Location_Interface.class);
       String url = String.format("json?location=%f,%f&radius=1500&type=bank&key=AIzaSyAGKJfnsFM5lV-LP0KTw5RhjlRbNqwi2Pc",lat,lng);
       final Call<NearbyLocation> nearbyLocationCall = location_interface.getLocationData(url);

       nearbyLocationCall.enqueue(new Callback<NearbyLocation>() {
           @Override
           public void onResponse(Call<NearbyLocation> call, Response<NearbyLocation> response) {

               if (response.code() ==200){
                   nearbyLocation =response.body();

                   list = new ArrayList<Result>();
                   list = nearbyLocation.getResults();
                   nearbyAdapter = new NearbyAdapter(NearBy_Location.this,list);
                   recyclerView.setLayoutManager(new LinearLayoutManager(NearBy_Location.this));
                   recyclerView.setAdapter(nearbyAdapter);
                   /*for (int i = 0; i < list.size(); i++){
                       double lat = nearbyLocation.getResults().get(i).getGeometry().getLocation().getLat();
                       double lng = nearbyLocation.getResults().get(i).getGeometry().getLocation().getLng();
                   }*/
               }

           }

           @Override
           public void onFailure(Call<NearbyLocation> call, Throwable t) {

               textView.setText(t.getMessage());

           }
       });

    }

    private void getMyCurrentLocation() {

        try {
            locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
        /*fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
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
        });*/
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
