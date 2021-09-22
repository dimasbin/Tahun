package com.example.tahun.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tahun.R;

import java.util.ArrayList;

public class CustomAdapterHari extends RecyclerView.Adapter<CustomAdapterHari.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id,idtahun,namabulan,namaitem,harga,tanggal;

    public CustomAdapterHari(Activity activity, Context context, ArrayList id, ArrayList idtahun, ArrayList namabulan, ArrayList namaitem, ArrayList harga, ArrayList tanggal) {
        this.context = context;
        this.activity = activity;
        this.id = id;
        this.idtahun = idtahun;
        this.namabulan = namabulan;
        this.namaitem = namaitem;
        this.harga = harga;
        this.tanggal = tanggal;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_hari,parent,false);
        return new CustomAdapterHari.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.idtahun.setText(String.valueOf(idtahun.get(position)));
        holder.namabulan.setText(String.valueOf(namabulan.get(position)));
        holder.namaitem.setText(String.valueOf(namaitem.get(position)));
        holder.harga.setText(String.valueOf(harga.get(position)));
        holder.tanggal.setText(String.valueOf(tanggal.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id,idtahun,namabulan,namaitem,harga,tanggal;
        LinearLayout mainLayout_hari;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.harian_id);
            idtahun = itemView.findViewById(R.id.harian_idhari);
            namabulan = itemView.findViewById(R.id.harian_namabulan);
            namaitem = itemView.findViewById(R.id.harian_nama_barang);
            harga = itemView.findViewById(R.id.harian_jumlah_barang);
            tanggal = itemView.findViewById(R.id.harian_tanggal);

            mainLayout_hari = itemView.findViewById(R.id.mainLayoutHari);
            Animation translate_anim = AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout_hari.setAnimation(translate_anim);
        }
    }
}
