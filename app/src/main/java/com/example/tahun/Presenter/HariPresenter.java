package com.example.tahun.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.tahun.HariActivity;
import com.example.tahun.Interface.HariContract;
import com.example.tahun.Model.mHari;

import java.util.ArrayList;

public class HariPresenter extends SQLiteOpenHelper implements HariContract.Presenter {

    HariContract.View view;
    ArrayList<String> id,id_tahun,nama_bulan,nama_item,harga_item,tanggal_item;
    Cursor cursor;

    public HariPresenter(Context context, HariContract.View view){
        super(context,"manageruang.db",null,1);
        this.view = view;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void tambahItem(Integer idtahun, String idbulan, String namaitem, Integer harga, String tanggal,Integer Total) {
        if (id_tahun.equals(null) || idbulan.equals(null) || namaitem.equals(null) || harga.equals(null) || tanggal.equals(null)){
            view.messageFailed("Gagal Menambah Tahun");
        } else {
            SQLiteDatabase sql = getWritableDatabase();
            mHari newHr = new mHari();
            newHr.setIdtahun(idtahun);
            newHr.setIdbulan(idbulan);
            newHr.setNamaitem(namaitem);
            newHr.setHarga(harga);
            newHr.setTanggal(tanggal);
            sql.execSQL("INSERT INTO hari(idtahun,idbulan,namaitem,harga,tanggal) VALUES('"+newHr.getIdtahun()+"','"+newHr.getIdbulan()+"','"+newHr.getNamaitem()+"','"+newHr.getHarga()+"','"+newHr.getTanggal()+"')");
            updateTotal(idtahun,idbulan,Total);
            view.messageSuccess("Berhasil Menambah Item");
            view.refreshPage();
        }
    }

    @Override
    public void updateTotal(Integer id,String nabul,Integer Total) {
        SQLiteDatabase sql = getWritableDatabase();
        Log.d("Isi pas update",id.toString());
        Log.d("Isi pas update",nabul);
        Log.d("Isi pas update",Total.toString());
        sql.execSQL("UPDATE bulan SET jumlah = "+Total+" WHERE idbulan = "+id+" AND bulan = "+"'"+nabul+"'");
        view.messageSuccess("Berhasil Update Harga");
    }

    @Override
    public void bacaItem(String kodetahun,String kodebulan) {
        id = new ArrayList<>();
        id_tahun = new ArrayList<>();
        nama_bulan = new ArrayList<>();
        nama_item = new ArrayList<>();
        harga_item = new ArrayList<>();
        tanggal_item = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = null;
        if (db != null){
            cursor = db.rawQuery("SELECT * FROM hari WHERE idtahun = "+kodetahun+" AND idbulan  = "+"'"+kodebulan+"'",null);
            if (cursor.getCount() == 0){
                view.messageFailed("Data Kosong");
            } else {
                while (cursor.moveToNext()){
                    id.add(cursor.getString(0));
                    id_tahun.add(cursor.getString(1));
                    nama_bulan.add(cursor.getString(2));
                    nama_item.add(cursor.getString(3));
                    harga_item.add(cursor.getString(4));
                    tanggal_item.add(cursor.getString(5));

                    view.readData(id,id_tahun,nama_bulan,nama_item,harga_item,tanggal_item);
                }
            }
        }

    }
}
