package com.example.tourttavels.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Model.BookModel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BookAdapter2 extends FirebaseRecyclerAdapter<BookModel, BookAdapter2.BookViewHolder> {

    public BookAdapter2(@NonNull FirebaseRecyclerOptions<BookModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull BookModel model) {
        holder.tvloca2.setText(model.getPack_name());
        holder.tvstat2.setText(model.getStatus());
        holder.tvpric2.setText(model.getPack_price());
        holder.tvuser2.setText(model.getUsername());
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.row_booking2, parent, false);
        return new BookViewHolder(itemView);
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvloca2, tvstat2, tvpric2, tvuser2;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvloca2 = itemView.findViewById(R.id.tvloca2);
            tvstat2 = itemView.findViewById(R.id.tvstat2);
            tvpric2 = itemView.findViewById(R.id.tvpric2);
            tvuser2 = itemView.findViewById(R.id.tvuser2);
        }
    }
}
