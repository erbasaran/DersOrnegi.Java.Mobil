package com.hr200012.bekir_berk_dundar_final.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;

import com.hr200012.bekir_berk_dundar_final.R;
import com.hr200012.bekir_berk_dundar_final.util.ConnectionHelper;
import com.hr200012.bekir_berk_dundar_final.util.DialogHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //oncreate: yaşam döngüsüne göre ilk başta sadece bir kere çalışır.

        //actionbar gizle
        getSupportActionBar().hide();

        //internet erişimi için gerekli
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onResume() {
        super.onResume();

        //onresume: yaşam döngüsüne göre sayfanın her aktif olması durumunda tekrar çalışır.
        //İnternet ayarlarına gönderme durumu olduğu için tekrar geri gelmesi durumunu göz önüne alarak burada kontrollerimizi yapıyoruz.

        //3.000 milisaniye sonra run metodu içindeki kodlar çalışır.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                internetControlAndGoToListScreen();
            }
        }, 3000);
    }

    private void internetControlAndGoToListScreen() {
        if (ConnectionHelper.InternetIsAvailable()) {
            //listeleme sayfasını tanımlayıp açıyoruz
            Intent listIntent = new Intent(SplashActivity.this, ListActivity.class);
            startActivity(listIntent);

            //splash ekranını kapatıyoruz
            finish();
        } else {
            //internet hatası olduğuna dair dialog popup mesajı göster
            DialogHelper.showInternetConnectionFaliedDialog(SplashActivity.this);
        }
    }
}