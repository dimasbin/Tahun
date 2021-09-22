package com.example.tahun.Model;

public class mBulan {
    String idbulan;
    String namabulan;
    int jumlah;

    public mBulan(){

    }

    public mBulan(String idbulan, String namabulan, int jumlah) {
        this.idbulan = idbulan;
        this.namabulan = namabulan;
        this.jumlah = jumlah;
    }

    public String getIdbulan() {
        return idbulan;
    }

    public void setIdbulan(String idbulan) {
        this.idbulan = idbulan;
    }

    public String getNamabulan() {
        return namabulan;
    }

    public void setNamabulan(String namabulan) {
        this.namabulan = namabulan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
