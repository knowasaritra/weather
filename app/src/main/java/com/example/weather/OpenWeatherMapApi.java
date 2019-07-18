package com.example.weather;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface OpenWeatherMapApi {

    @GET("data/2.5/forecast")
    Call<List<Post>> getPosts(@QueryMap Map<String,String> parameters);
}
