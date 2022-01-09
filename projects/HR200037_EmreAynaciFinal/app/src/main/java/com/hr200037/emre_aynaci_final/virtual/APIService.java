package com.hr200037.emre_aynaci_final.virtual;

import com.hr200037.emre_aynaci_final.model.Footballer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("data.json")
    Call<List<Footballer>> getData();
}