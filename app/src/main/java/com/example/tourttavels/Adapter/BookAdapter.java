package com.example.tourttavels.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Model.BookModel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BookAdapter  extends FirebaseRecyclerAdapter<BookModel, BookAdapter.BookViewHolder> {
    private final Context context;



    public BookAdapter(@NonNull FirebaseRecyclerOptions<BookModel> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull BookModel model) {
        holder.tvloca.setText("Location :- "+model.getPack_name());
        holder.tvpric.setText("Price :- "+model.getTotal_amount());
        holder.tvdate.setText("Date :- "+model.getDate());

        holder.btncancel.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            builder.setTitle("Are you sure you want to delete the booking?");
            builder.setMessage("Deletion can't be undone!");

            builder.setPositiveButton("Delete", (dialogInterface, i) -> {
                // Remove the booking from Firebase
                FirebaseDatabase.getInstance().getReference()
                        .child("booking") // Ensure this matches your database structure
                        .child(getRef(position).getKey())
                        .removeValue()
                        .addOnSuccessListener(aVoid -> Toast.makeText(context, "Booking deleted", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(context, "Failed to delete booking", Toast.LENGTH_SHORT).show());
            });

            builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
            });

            builder.show();
        });
        
//        holder.textViewCategory.setText(model.getCat_name());
//        Glide.with(holder.textViewCategory.getContext())
//                .load(model.getCat_pic()).into(holder.imageViewCategory);

    }



    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.row_booking, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(photoView);
        return viewHolder;

    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvloca,tvdate,tvpric;
        Button btncancel;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvloca=itemView.findViewById(R.id.tvloca);
            tvpric=itemView.findViewById(R.id.tvpric);
            tvdate=itemView.findViewById(R.id.tvdate);
            btncancel=itemView.findViewById(R.id.btncancel);
            
            
            
        }
    }

}

//        ImageView imageViewOfCategory;
//        TextView textViewOfCategoryName;
//
//        public CategoryHolder(@NonNull View itemView) {
//            super(itemView);
//            imageViewOfCategory = itemView.findViewById(R.id.image_view_row_category_ui);
//            textViewOfCategoryName = itemView.findViewById(R.id.tv_row_category_ui);
//        }

// ==========================================================


//ArrayList<CategoryModel> list;
//Context context;
//
//public CategoryAdapter(ArrayList<CategoryModel> list, Context context) {
//    this.list = list;
//    this.context = context;
//}
//
//@NonNull
//@Override
//public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//    Context con = parent.getContext();
//    LayoutInflater layoutInflater =LayoutInflater.from(con);
//    View view = layoutInflater.inflate(R.layout.row_category_ui, parent, false);
//    CategoryHolder categoryHolder =new CategoryHolder(view);
//    return categoryHolder;
//}
//
//@Override
//public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
//    CategoryModel categoryModel =list.get(position);
//    holder.imageViewOfCategory.setImageResource(categoryModel.getCategoryImage());
//    holder.textViewOfCategoryName.setText(categoryModel.getCategoryName());
//    // Glide.with(context).load(categoryModel.getCategoryImage()).into(holder.imageViewOfCategory);
//}
//
//@Override
//public int getItemCount() {
//    return list.size();
//}
