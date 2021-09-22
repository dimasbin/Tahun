package com.example.tahun.Model;

public class mTahun {
    Integer idTahun;
    String namaTahun;

    public  mTahun(){

    }
    public mTahun(Integer idTahun, String namaTahun) {
        this.idTahun = idTahun;
        this.namaTahun = namaTahun;
    }

    public Integer getIdTahun() {
        return idTahun;
    }

    public void setIdTahun(Integer idTahun) {
        this.idTahun = idTahun;
    }

    public String getNamaTahun() {
        return namaTahun;
    }

    public void setNamaTahun(String namaTahun) {
        this.namaTahun = namaTahun;
    }
}
