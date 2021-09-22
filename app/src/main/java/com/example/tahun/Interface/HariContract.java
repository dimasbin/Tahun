package com.example.tahun.Interface;

import java.util.ArrayList;

public interface HariContract {
    interface View{
        void refreshPage();
        void readData(ArrayList id, ArrayList idtahun, ArrayList idbulan,ArrayList namaitem,ArrayList harga,ArrayList tanggal);
        void messageSuccess(String message);
        void messageFailed(String message);
    }

    interface Presenter{
        void tambahItem(Integer idtahun,String idbulan,String namaitem, Integer harga, String tanggal);
        void updateTotal();
        void bacaItem(String id,String nama);
    }
}
