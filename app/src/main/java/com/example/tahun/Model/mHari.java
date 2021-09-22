package com.example.tahun.Model;

public class mHari {
    Integer id;
    Integer idtahun;
    String idbulan;
    String namaitem;
    Integer harga;
    String tanggal;

    public  mHari(){

    }

    public mHari(Integer id, Integer idtahun, String idbulan, String namaitem, Integer harga, String tanggal) {
        this.id = id;
        this.idtahun = idtahun;
        this.idbulan = idbulan;
        this.namaitem = namaitem;
        this.harga = harga;
        this.tanggal = tanggal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdtahun() {
        return idtahun;
    }

    public void setIdtahun(Integer idtahun) {
        this.idtahun = idtahun;
    }

    public String getIdbulan() {
        return idbulan;
    }

    public void setIdbulan(String idbulan) {
        this.idbulan = idbulan;
    }

    public String getNamaitem() {
        return namaitem;
    }

    public void setNamaitem(String namaitem) {
        this.namaitem = namaitem;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
