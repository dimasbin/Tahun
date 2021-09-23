package com.example.tahun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tahun.Adapter.CustomAdapter;
import com.example.tahun.Interface.TahunContract;
import com.example.tahun.Presenter.TahunPresenter;

import java.util.ArrayList;

public class TahunActivity extends AppCompatActivity implements TahunContract.View {

    TahunContract.Presenter presenter;
    RecyclerView recyclerView;
    protected Cursor cursor;

    public static TahunActivity tahunActivity;
    private EditText namaTahun;
    private Button btnTambahTahun;

    CustomAdapter customAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahun);
        presenter = new TahunPresenter(this,this);

        namaTahun = findViewById(R.id.et_tambahTahun);
        btnTambahTahun = findViewById(R.id.button_tambahTahun);
        recyclerView = findViewById(R.id.list_tahun);
        btnTambahTahun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.tambahTahun(Integer.valueOf(namaTahun.getText().toString().trim()),namaTahun.getText().toString().trim());

            }
        });

        presenter.bacaTahun();

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
    public void readData(ArrayList id,ArrayList id_tahun,ArrayList nama_tahun) {
        customAdapter = new CustomAdapter(TahunActivity.this,this,id,id_tahun,nama_tahun);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TahunActivity.this));
        tahunActivity = this;
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