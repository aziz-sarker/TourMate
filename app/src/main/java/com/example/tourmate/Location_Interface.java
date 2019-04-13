package com.example.tourmate;

import com.example.tourmate.NearbyLocation.NearbyLocation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Location_Interface {
    @GET
    Call<NearbyLocation> getLocationData(@Url String url);
}
