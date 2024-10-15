package com.example.tourttavels.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Model.BookModel;
import com.example.tourttavels.Model.categorymodel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BookAdapter  extends FirebaseRecyclerAdapter<BookModel, BookAdapter.BookViewHolder> {

    public BookAdapter(@NonNull FirebaseRecyclerOptions<BookModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull BookModel model) {
        holder.tvloca.setText(model.getPack_name());
        holder.tvstat.setText(model.getStatus());
        holder.tvpric.setText(model.getPack_price());
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
        TextView tvloca,tvstat,tvpric;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvloca=itemView.findViewById(R.id.tvloca);
            tvstat=itemView.findViewById(R.id.tvstat);
            tvpric=itemView.findViewById(R.id.tvpric);

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
