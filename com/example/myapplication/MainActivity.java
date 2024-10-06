package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    CardView laundry, layanan, pelanggan, promo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        laundry = (CardView) findViewById(R.id.laundry);
        layanan = (CardView) findViewById(R.id.layanan);
        pelanggan = (CardView) findViewById(R.id.pelanggan);
        promo = (CardView) findViewById(R.id.promo);

        //even handling
        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LoginActivity.this, "uwaww", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, transaksi.class);
                startActivity(intent);
                finish();
            }
        });


        //even handling
        layanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LoginActivity.this, "uwaww", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, com.example.myapplication.layanan.class);
                startActivity(intent);
                finish();
            }
        });

        //even handling
        pelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LoginActivity.this, "uwaww", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, tambahanPelanggan.class);
                startActivity(intent);
                finish();
            }
        });

        //even handling
        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LoginActivity.this, "uwaww", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, promo.class);
                startActivity(intent);
                finish();
            }
        });
    }
}