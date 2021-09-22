package com.example.tahun.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.tahun.BulanActivity;
import com.example.tahun.Interface.BulanContract;
import com.example.tahun.Interface.TahunContract;

import java.util.ArrayList;

public class BulanPresenter extends SQLiteOpenHelper implements BulanContract.Presenter {

    BulanContract.View view;
    ArrayList<String> id,idbulan,namabulan,jumlah;
    Cursor cursor;

    public BulanPresenter(Context context, BulanContract.View view){
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
    public void bacaBulan(String kodebulan) {
        id = new ArrayList<>();
        idbulan = new ArrayList<>();
        namabulan = new ArrayList<>();
        jumlah = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = null;
        if (db != null){
            cursor = db.rawQuery("SELECT * FROM bulan WHERE idbulan ="  + kodebulan+"",null);
            if (cursor.getCount() == 0){
                view.messageFailed("Data Kosong");
            } else {
                while (cursor.moveToNext()){
                    id.add(cursor.getString(0));
                    idbulan.add(cursor.getString(1));
                    namabulan.add(cursor.getString(2));
                    jumlah.add(cursor.getString(3));
                    view.readData(id,idbulan,namabulan,jumlah);
                }
            }
        }

    }
}
