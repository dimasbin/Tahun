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

import com.example.tahun.BulanActivity;
import com.example.tahun.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id,id_tahun,nama_tahun;

    public CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList id_tahun, ArrayList nama_tahun) {
        this.context = context;
        this.activity = activity;
        this.id = id;
        this.id_tahun = id_tahun;
        this.nama_tahun = nama_tahun;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tahun,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.idtahun.setText(String.valueOf(id_tahun.get(position)));
        holder.nama_tahun.setText(String.valueOf(nama_tahun.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BulanActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("id_tahun",String.valueOf(id_tahun.get(position)));
                intent.putExtra("nama_tahun",String.valueOf(nama_tahun.get(position)));
                activity.startActivityForResult(intent,1);


            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nama_tahun,id,idtahun;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_tahun = itemView.findViewById(R.id.list_nama_tahun);
            id = itemView.findViewById(R.id.id);
            idtahun = itemView.findViewById(R.id.idtahun);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            Animation translate_anim = AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
