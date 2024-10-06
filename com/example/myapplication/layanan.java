package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.AdapterLayanan;
import adapter.AdapterPelanggan;
import database.SQLiteHelper;
import database.SQLiteHelper2;
import model.ModelLayanan;
import model.ModelPelanggan;
import pelanggan.PelangganAddActivity;
public class layanan extends AppCompatActivity{
    SQLiteHelper2 db;
    Button btnInfo;
    RecyclerView rvLayanan;
    AdapterLayanan adapterLayanan;
    ArrayList<ModelLayanan> list;
    ProgressDialog progressDialog;
    AlphaAnimation btnAnimasi = new AlphaAnimation(1F,0.5F);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_layanan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars =insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                            systemBars.bottom);
                    return insets;
                });

        setView();
        eventHandling();
        getData();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.startAnimation(btnAnimasi);
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            ModelLayanan ml = list.get(position);
            Toast.makeText(layanan.this, "Layanan: " + ml.getNama(), // Ganti nama
                    Toast.LENGTH_SHORT).show();
        }
    };

    private void getData() {
        list.clear(); // Bersihkan list sebelum mengambil data baru
        showMsg(); // Tampilkan loading dialog
        progressDialog.dismiss(); // Sembunyikan loading dialog setelah data berhasil diambil

        try {
            List<ModelLayanan> l = db.getModelLayanan(); // Ambil data dari database
            if (l.size() > 0) {
                for (ModelLayanan lay : l) {
                    ModelLayanan ml = new ModelLayanan();
                    ml.setId(lay.getId());
                    ml.setNama(lay.getNama());
                    ml.setHarga(lay.getHarga());
                    list.add(ml); // Tambahkan data ke list
                }

                adapterLayanan = new AdapterLayanan(this, list);
                adapterLayanan.notifyDataSetChanged();
                rvLayanan.setAdapter(adapterLayanan);

                adapterLayanan.setOnItemClickListener(onClickListener);
            } else {
                Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eventHandling() {
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(layanan.this, LayananAddActivity.class));

            }
        });
    }

    private void setView() {
        db = new SQLiteHelper2(this); // Inisialisasi SQLiteHelper
        progressDialog = new ProgressDialog(this); // Inisialisasi ProgressDialog
        btnInfo = findViewById(R.id.btnInfo); // Temukan tombol tambah pelanggan dari layout
        rvLayanan = findViewById(R.id.rvLayanan); // Temukan RecyclerView dari layout
        list = new ArrayList<>(); // Inisialisasi list untuk menampung data pelanggan

        // Inisialisasi LinearLayoutManager untuk RecyclerView
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL); // Mengatur orientasi menjadi vertikal
        rvLayanan.setHasFixedSize(true); // Optimalkan ukuran RecyclerView
        rvLayanan.setLayoutManager(llm); // Set layout manager ke RecyclerView
    }

    private void showMsg() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Informasi");
            progressDialog.setMessage("Loading Data...");
            progressDialog.setCancelable(false); // Tidak bisa dibatalkan dengan menekan di luar
        }
        progressDialog.show(); // Tampilkan dialog
    }
}
