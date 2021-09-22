package com.example.tahun.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.tahun.Adapter.CustomAdapter;
import com.example.tahun.DataHelperTahun;
import com.example.tahun.Interface.TahunContract;
import com.example.tahun.TahunActivity;

import java.util.ArrayList;

public class TahunPresenter extends SQLiteOpenHelper implements TahunContract.Presenter {

    TahunContract.View view;
    Context context;
    ArrayList<String> id,id_tahun,nama_tahun;
    CustomAdapter customAdapter;
    Cursor cursor;
    DataHelperTahun dbTblTahun;

    public TahunPresenter(Context context, TahunContract.View view){
        super(context,"manageruang.db",null,1);
        this.view = view;

    }

    @Override
    public void tambahTahun(Integer id,String nama) {
        if (id.equals(null) || nama.equals(null)){
            view.messageFailed("Gagal Menambah Tahun");
        } else {
            SQLiteDatabase sql = getWritableDatabase();
            sql.execSQL("INSERT INTO tahun(idtahun,tahun) VALUES('"+id+"','"+nama+"')");
            String [] namaBulan = {"Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember"};
            for (int i = 0;i < namaBulan.length; i++){
                sql.execSQL("INSERT INTO bulan(idbulan,bulan,jumlah) VALUES('"+nama+"','"+namaBulan[i]+"','"+0+"')");
                Log.d("Database Bulan","BerhasiL Menambah "+namaBulan[i]);
            }
//            DataHelperTahun myDB = new DataHelperTahun();
//            myDB.tambahTahun(id,nama);
            view.messageSuccess("Berhasil Menambah Tahun");
            view.refreshPage();
        }

    }

    @Override
    public void bacaTahun() {
        id = new ArrayList<>();
        id_tahun = new ArrayList<>();
        nama_tahun = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = null;
        if (db != null){
           cursor = db.rawQuery("SELECT * FROM tahun",null);
            if (cursor.getCount() == 0){
                Toast.makeText(context, "Data Kosong", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()){
                    id.add(cursor.getString(0));
                    id_tahun.add(cursor.getString(1));
                    nama_tahun.add(cursor.getString(2));
                    view.readData(id,id_tahun,nama_tahun);
                }
            }
        }



    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
