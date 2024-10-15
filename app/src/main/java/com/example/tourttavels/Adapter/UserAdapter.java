package com.example.tourttavels.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Model.UserModel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class UserAdapter extends FirebaseRecyclerAdapter<UserModel, UserAdapter.UserViewHolder> {
    private final Context context;

    public UserAdapter(@NonNull FirebaseRecyclerOptions<UserModel> options) {
        super(options);
        this.context = null;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull UserModel model) {
        // Bind the data to the view holder
        holder.tvfullname.setText("Name: " + model.getFullname());
        holder.tvname.setText("Username: " + model.getUsername());
        holder.tvemail.setText("Email: " + model.getEmail());
        holder.tvpassword.setText("Password: " + model.getPassword());
        holder.tvmobile.setText("Mobile No: " + model.getPhone());

        // Assuming you have an image URL or resource for user profile
        // If you have an image URL in your UserModel, you can load it using Glide

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new UserViewHolder(view);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvname, tvemail, tvfullname, tvmobile,tvpassword;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.uname);
            tvemail = itemView.findViewById(R.id.uemail);
            tvfullname = itemView.findViewById(R.id.ufullname);
            tvpassword = itemView.findViewById(R.id.upassword);
            tvmobile = itemView.findViewById(R.id.uphone);
        }
    }
}
