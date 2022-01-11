package com.hr200037.emre_aynaci_final.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hr200037.emre_aynaci_final.R;
import com.hr200037.emre_aynaci_final.adapter.ListRecyclerViewAdapter;
import com.hr200037.emre_aynaci_final.model.Footballer;
import com.hr200037.emre_aynaci_final.util.DialogHelper;
import com.hr200037.emre_aynaci_final.virtual.APIService;
import com.hr200037.emre_aynaci_final.virtual.ClickListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {
    ListRecyclerViewAdapter recyclerViewAdapter;
    List<Footballer> footballerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initialize();

        getFootballerData();
    }
    
    private void initialize(){
        getSupportActionBar().setTitle(getString(R.string.list_screen_title));

        footballerList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new ListRecyclerViewAdapter(footballerList);

        //listede bir elemana tıklandığı anda çalışacak listener
        recyclerViewAdapter.setOnItemClickListener(new ClickListener<Footballer>() {
            @Override
            public void onItemClick(Footballer data) {
                //Tıklanan data json stringe çevrilip detay sayfasına gönderiliyor.
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("data", (new Gson()).toJson(data));
                startActivity(intent);
            }
        });

        //adaptör recycler viewe bağlanıyor
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void getFootballerData() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(getString(R.string.api_url)).addConverterFactory(GsonConverterFactory.create(gson)).build();

        APIService apis = retrofit.create(APIService.class);
        Call<List<Footballer>> call = apis.getData();
        call.enqueue(new Callback<List<Footballer>>() {
            @Override
            public void onResponse(Call<List<Footballer>> call, Response<List<Footballer>> response) {
                //veriler başarıyla alındı
                footballerList.addAll(response.body()); //gelen verileri listemize ekle
                recyclerViewAdapter.notifyDataSetChanged(); //adaptörü yenile
            }

            @Override
            public void onFailure(Call<List<Footballer>> call, Throwable t) {
                //veriler alınırken hata oluştu
                Toast.makeText(ListActivity.this, getString(R.string.list_screen_data_error_text), Toast.LENGTH_LONG).show(); //hata mesajı göster
                Log.d("retrofit error", t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DialogHelper.areYouSureExit(ListActivity.this);
    }
}
