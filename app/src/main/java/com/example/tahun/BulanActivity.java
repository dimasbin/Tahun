package com.example.tahun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tahun.Adapter.CustomAdapterBulan;
import com.example.tahun.Interface.BulanContract;
import com.example.tahun.Interface.TahunContract;
import com.example.tahun.Presenter.BulanPresenter;

import java.util.ArrayList;

public class BulanActivity extends AppCompatActivity implements BulanContract.View {

    RecyclerView recyclerView;
    DataHelperTahun dbTblBulan;
    CustomAdapterBulan customAdapterBulan;
    public static BulanActivity bulanActivity;
    String idBul;
    BulanContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulan);
        presenter = new BulanPresenter(this,this);

        dbTblBulan = new DataHelperTahun(this);
        recyclerView = findViewById(R.id.recyclerView_Bulan);
        Log.d("Isi Yang Di klik",getIntent().getStringExtra("id"));
        Log.d("Isi Yang Di klik",getIntent().getStringExtra("id_tahun"));
        Log.d("Isi Yang Di klik",getIntent().getStringExtra("nama_tahun"));
        idBul = getIntent().getStringExtra("id_tahun");
        presenter.bacaBulan(idBul);



    }


    @Override
    public void messageSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void messageFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void readData(ArrayList id, ArrayList idbulan, ArrayList namabulan, ArrayList jumlah) {
        customAdapterBulan = new CustomAdapterBulan(BulanActivity.this,this,id,idbulan,namabulan,jumlah);
        recyclerView.setAdapter(customAdapterBulan);
        recyclerView.setLayoutManager(new LinearLayoutManager(BulanActivity.this));
        bulanActivity = this;
    }
}