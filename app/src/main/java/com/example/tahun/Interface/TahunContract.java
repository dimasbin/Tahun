package com.example.tahun.Interface;

import java.util.ArrayList;

public interface TahunContract {
    interface View{
        void refreshPage();
        void readData(ArrayList id,ArrayList id2,ArrayList nama);
        void messageSuccess(String message);
        void messageFailed(String message);
    }

    interface Presenter{
        void tambahTahun(Integer id,String nama);
        void bacaTahun();
    }
}
