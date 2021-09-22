package com.example.tahun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tahun.Adapter.CustomAdapterHari;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HariActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    protected Cursor cursor;
    DataHelperTahun dbTblHari;

    public static HariActivity hariActivity;
    private TextView total;
    private EditText namaItem,jumlahItem;
    private Button btnTambahItem;

    ArrayList<String> id,id_tahun,nama_bulan,nama_item,harga_item,tanggal_item;
    CustomAdapterHari customAdapterHari;

    String idTahun,namaBulan,Tanggal,isiNamaBarang,isiHargaBarang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hari);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Tanggal = simpleDateFormat.format(calendar.getTime());

        Log.d("Isi Yang Di klik HARIAN",getIntent().getStringExtra("id"));
        Log.d("Isi Yang Di klik HARIAN",getIntent().getStringExtra("id_tahun"));
        Log.d("Isi Yang Di klik HARIAN",getIntent().getStringExtra("nama_bulan"));
        Log.d("Isi Yang Di klik HARIAN",getIntent().getStringExtra("jumlah"));

        recyclerView = findViewById(R.id.recyclerView_Hari);
        hariActivity = this;
        namaItem = findViewById(R.id.et_nama_item);
        jumlahItem = findViewById(R.id.et_harga_item);
        btnTambahItem = findViewById(R.id.btn_tambah_item);
        total = findViewById(R.id.tv_total_harga);

        idTahun = getIntent().getStringExtra("id_tahun");
        namaBulan = getIntent().getStringExtra("nama_bulan");


        dbTblHari = new DataHelperTahun(HariActivity.this);
        id = new ArrayList<>();
        id_tahun = new ArrayList<>();
        nama_bulan = new ArrayList<>();
        nama_item = new ArrayList<>();
        harga_item = new ArrayList<>();
        tanggal_item = new ArrayList<>();

        btnTambahItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataHelperTahun myDB = new DataHelperTahun(HariActivity.this);
                Log.d("Isi Klik Tambah Item",idTahun);
                Log.d("Isi Klik Tambah Item",namaBulan);
                Log.d("Isi Klik Tambah Item",namaItem.getText().toString());
                Log.d("Isi Klik Tambah Item",jumlahItem.getText().toString());
                Log.d("Isi Klik Tambah Item",Tanggal);
                myDB.tambahItem(Integer.valueOf(idTahun),namaBulan,namaItem.getText().toString().trim(), Integer.valueOf(jumlahItem.getText().toString().trim()),Tanggal);
                finish();

            }
        });



        storeDatainArray(idTahun,namaBulan);

        customAdapterHari = new CustomAdapterHari(HariActivity.this,this,id,id_tahun,nama_bulan,nama_item,harga_item,tanggal_item);
        recyclerView.setAdapter(customAdapterHari);
        recyclerView.setLayoutManager(new LinearLayoutManager(HariActivity.this));
        hariActivity = this;



    }

    private void storeDatainArray(String idTahun,String namaBulan) {
        cursor = dbTblHari.readHari(idTahun,namaBulan);
        if (cursor.getCount() == 0){
            Toast.makeText(HariActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                id_tahun.add(cursor.getString(1));
                nama_bulan.add(cursor.getString(2));
                nama_item.add(cursor.getString(3));
                harga_item.add(cursor.getString(4));
                tanggal_item.add(cursor.getString(5));
            }
        }
    }
}