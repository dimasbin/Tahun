package com.example.tahun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.tahun.Model.mBulan;
import com.example.tahun.Model.mHari;
import com.example.tahun.Model.mTahun;

public class DataHelperTahun extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "manageruang.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    //Tabel Tahun
    private static final String Tabel_Tahun = "tahun";
    private static final String ID = "id";
    private static final String IDTAHUN = "idtahun";
    private static final String NAMA_TAHUN = "tahun";

    //Tabel Bulan
    private static final String Tabel_Bulan = "bulan";
    private static final String IDBULAN = "idbulan";
    private static final String NAMA_BULAN = "bulan";
    private static final String JUMLAH = "jumlah";

    //Tabel Hari
    private static final String Tabel_Hari = "hari";
    private static final String IDHARI = "idtahun";
    private static final String NAMA_ITEM = "namaitem";
    private static final String HARGA = "harga";
    private static final String TANGGAL = "tanggal";

    public DataHelperTahun(@Nullable Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String query1 = "CREATE TABLE "+Tabel_Tahun+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+IDTAHUN+ " INTEGER, "+NAMA_TAHUN+" TEXT); ";
       String query2 = "CREATE TABLE "+Tabel_Bulan+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+IDBULAN+" INTEGER, "+NAMA_BULAN+" TEXT, "+JUMLAH+" INTEGER);";
       String query3 = "CREATE TABLE "+Tabel_Hari+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+IDHARI+" INTEGER,"+IDBULAN+" TEXT, "+NAMA_ITEM+" TEXT,"+HARGA+" INTEGER, "+TANGGAL+" TEXT);";

       sqLiteDatabase.execSQL(query1);
       sqLiteDatabase.execSQL(query2);
       sqLiteDatabase.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int arg1, int arg2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Tabel_Tahun);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Tabel_Bulan);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Tabel_Hari);
        onCreate(sqLiteDatabase);
    }

    public void tambahTahun(Integer idTahun, String namaTahun){
        Log.d("Isi Data",idTahun.toString());
        Log.d("Isi Data",namaTahun);
        SQLiteDatabase db = this.getWritableDatabase();
        mTahun tahun = new mTahun();
        tahun.setIdTahun(idTahun);
        tahun.setNamaTahun(namaTahun);
        db.execSQL("INSERT INTO tahun(idtahun,tahun) VALUES('"+idTahun+"','"+namaTahun+"')");
        Toast.makeText(context, "Berhasil Menambah Tahun", Toast.LENGTH_SHORT).show();
        Log.d("Database","BerhasiL DItambah");
        tambahBulan(namaTahun);

    }

    void tambahBulan(String nama_bulan){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] namaBulan = {"Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember"};
        for (int i = 0;i < namaBulan.length; i++){
            db.execSQL("INSERT INTO bulan(idbulan,bulan,jumlah) VALUES('"+nama_bulan+"','"+namaBulan[i]+"','"+0+"')");
            Log.d("Database Bulan","BerhasiL Menambah "+namaBulan[i]);
        }

        Toast.makeText(context, "Berhasil Menambah Bulan", Toast.LENGTH_SHORT).show();

    }

    void tambahItem(Integer idtahun,String namabulan,String namaitem,Integer harga,String tanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        mHari hari = new mHari();
        hari.setIdtahun(idtahun);
        hari.setIdbulan(namabulan);
        hari.setNamaitem(namaitem);
        hari.setHarga(harga);
        hari.setTanggal(tanggal);
        db.execSQL("INSERT INTO hari(idtahun,idbulan,namaitem,harga,tanggal) VALUES('"+idtahun+"','"+namabulan+"','"+namaitem+"','"+harga+"','"+tanggal+"')");
        Toast.makeText(context, "Berhasil Tambah Item", Toast.LENGTH_SHORT).show();


    }



    public Cursor readTahun(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery("SELECT * FROM tahun",null);
        }
        return cursor;
    }

    Cursor readBulan(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery("SELECT * FROM bulan WHERE idbulan = "+id+"" ,null);
        }
        return cursor;
    }

    Cursor readHari(String id,String nama){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery("SELECT * FROM hari WHERE idtahun = "+id+" AND idbulan  = "+"'"+nama+"'",null);
        }
        return cursor;
    }





}
