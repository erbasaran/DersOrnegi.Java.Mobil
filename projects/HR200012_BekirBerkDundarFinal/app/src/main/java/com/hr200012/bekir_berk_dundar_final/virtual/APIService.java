package com.hr200012.bekir_berk_dundar_final.virtual;

import com.hr200012.bekir_berk_dundar_final.model.Footballer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("data.json")
    Call<List<Footballer>> getData();
}