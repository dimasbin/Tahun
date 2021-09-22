package com.example.tahun.Interface;

import android.widget.Toast;

import com.example.tahun.BulanActivity;

import java.util.ArrayList;

public interface BulanContract {

    interface View{
        void messageSuccess(String message);
        void messageFailed(String message);
        void readData(ArrayList id,ArrayList idbulan, ArrayList namabulan, ArrayList jumlah);

    }

    interface Presenter{
        void bacaBulan(String id);
    }
}
