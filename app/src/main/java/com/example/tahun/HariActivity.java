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
import com.example.tahun.Interface.HariContract;
import com.example.tahun.Presenter.HariPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HariActivity extends AppCompatActivity implements HariContract.View {

    RecyclerView recyclerView;
    DataHelperTahun dbTblHari;
    HariContract.Presenter presenter;

    public static HariActivity hariActivity;
    private TextView total;
    private EditText namaItem,jumlahItem;
    private Button btnTambahItem;
    CustomAdapterHari customAdapterHari;

    String idTahun,namaBulan,Tanggal,totalSekarang,isi;
    Integer currentValue,Total,isiJumlah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hari);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Tanggal = simpleDateFormat.format(calendar.getTime());

        presenter = new HariPresenter(this,this);

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
        totalSekarang = getIntent().getStringExtra("jumlah");
        total.setText(totalSekarang);

        presenter.bacaItem(idTahun,namaBulan);






        btnTambahItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isi = jumlahItem.getText().toString();
                currentValue = Integer.parseInt(totalSekarang);
                Total = (Integer.valueOf(isi)+currentValue);
                Log.d("Isi isi",isi);
                Log.d("Isi current Value",String.valueOf(currentValue));
                Log.d("Isi Jumlah",String.valueOf(Total));
                total.setText(String.valueOf(Total));
                presenter.tambahItem(Integer.valueOf(idTahun),namaBulan,namaItem.getText().toString().trim(),Integer.valueOf(jumlahItem.getText().toString().trim()),Tanggal,Total);
            }
        });
    }

    @Override
    public void refreshPage() {
        finish();
        recreate();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    @Override
    public void readData(ArrayList id, ArrayList idtahun, ArrayList idbulan, ArrayList namaitem, ArrayList harga, ArrayList tanggal) {
        customAdapterHari = new CustomAdapterHari(HariActivity.this,this,id,idtahun,idbulan,namaitem,harga,tanggal);
        recyclerView.setAdapter(customAdapterHari);
        recyclerView.setLayoutManager(new LinearLayoutManager(HariActivity.this));
        hariActivity = this;
    }

    @Override
    public void messageSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void messageFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}