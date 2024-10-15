package com.example.tourttavels.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tourttavels.Model.popularmodel;
import com.example.tourttavels.PackDetail;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class popularAdapter extends FirebaseRecyclerAdapter<popularmodel,popularAdapter.popularViewHolder> {

    public popularAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull popularViewHolder holder, int position, @NonNull popularmodel model) {
        holder.tittle.setText(model.getTittle());
        holder.location.setText(model.getLocation());
        holder.score.setText(model.getScore());
        Glide.with(holder.picimg.getContext()).load(model.getPic()).into(holder.picimg);

        holder.c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(holder.c.getContext(), PackDetail.class);
                intent.putExtra("pack",model);
                holder.c.getContext().startActivity(intent);

            }
        });




    }

    @NonNull
    @Override
    public popularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_popular,parent,false);
        return new popularViewHolder(view);
    }

    class popularViewHolder extends RecyclerView.ViewHolder {
        TextView tittle,location,description,bed,guide,pic,wifi,duration,category,popular,price,score;
        ImageView picimg;
        ConstraintLayout c;


        public popularViewHolder(@NonNull View itemView) {
            super(itemView);
            c = itemView.findViewById(R.id.c);
            tittle = itemView.findViewById(R.id.tittletxt);
            location = itemView.findViewById(R.id.locationtxt);
            picimg = itemView.findViewById(R.id.picimg);
            score = itemView.findViewById(R.id.scoretxt);

        }
    }
}
