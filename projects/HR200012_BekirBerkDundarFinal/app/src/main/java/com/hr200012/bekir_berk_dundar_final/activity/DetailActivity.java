package com.hr200012.bekir_berk_dundar_final.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hr200012.bekir_berk_dundar_final.R;
import com.hr200012.bekir_berk_dundar_final.model.Footballer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DetailActivity extends AppCompatActivity {

    TextView txtTitle, txtDescription;
    ImageView imgCover;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initialize();

        setData();
    }
    
    private void initialize(){
        //actionbar title değiştiriliyor
        getSupportActionBar().setTitle(getString(R.string.detail_screen_title));

        //actionbar sol taraftaki geri butonu aktifleştiriliyor.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtTitle = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        imgCover = findViewById(R.id.imgCover);
    }

    private void setData(){
        //listeleme sayfasından gönderilen veri alınıyor.
        String dataStr = getIntent().getStringExtra("data");
        if (dataStr == null || dataStr.equals("")){
            //veri alınamadığı için hata mesajı bastırılıyor
            Toast.makeText(DetailActivity.this, getString(R.string.detail_screen_data_error_text), Toast.LENGTH_LONG).show();

            //sayfa kapatılıyor ve bir önceki ekrana dönülüyor.
            finish();
        }else{
            //gelen string veri objeye dönüştürülüyor.
            Footballer data = (new Gson()).fromJson(dataStr, new TypeToken<Footballer>() {}.getType());

            txtTitle.setText(data.name);
            txtDescription.setText(Html.fromHtml(data.description));
            Glide.with(DetailActivity.this)
                    .load(data.cover)
                    .into(imgCover);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //actionbar soldaki geri butonuna tıkladı.
            //sayfayı geri gönderiyoruz.
            onBackPressed();
            return true;
        }
        return false;
    }
}
