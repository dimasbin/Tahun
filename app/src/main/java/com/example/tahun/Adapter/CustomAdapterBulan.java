package com.example.tahun.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tahun.HariActivity;
import com.example.tahun.R;

import java.util.ArrayList;

public class CustomAdapterBulan extends RecyclerView.Adapter<CustomAdapterBulan.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id,id_bulan,nama_bulan,jumlah;

    public CustomAdapterBulan(Activity activity, Context context, ArrayList id, ArrayList id_bulan, ArrayList nama_bulan, ArrayList jumlah) {
        this.context = context;
        this.activity = activity;
        this.id = id;
        this.id_bulan = id_bulan;
        this.nama_bulan = nama_bulan;
        this.jumlah = jumlah;

    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_bulan,parent,false);
        return new CustomAdapterBulan.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.id_bulan.setText(String.valueOf(id_bulan.get(position)));
        holder.namaBulan.setText(String.valueOf(nama_bulan.get(position)));
        holder.jumlah.setText(String.valueOf(jumlah.get(position)));
        holder.mainLayout_bulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HariActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("id_tahun",String.valueOf(id_bulan.get(position)));
                intent.putExtra("nama_bulan",String.valueOf(nama_bulan.get(position)));
                intent.putExtra("jumlah",String.valueOf(jumlah.get(position)));
                activity.startActivityForResult(intent,1);


            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id,id_bulan,namaBulan,jumlah;
        LinearLayout mainLayout_bulan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_sembunyi);
            id_bulan = itemView.findViewById(R.id.idbulan_sembunyi);
            namaBulan = itemView.findViewById(R.id.list_nama_bulan);
            jumlah = itemView.findViewById(R.id.jumlah_pengeluaran_bulan);

            mainLayout_bulan = itemView.findViewById(R.id.mainLayoutBulan);
            Animation translate_anim = AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout_bulan.setAnimation(translate_anim);
        }
    }
}


