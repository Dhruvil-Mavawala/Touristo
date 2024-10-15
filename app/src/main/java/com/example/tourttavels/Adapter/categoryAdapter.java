package com.example.tourttavels.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tourttavels.Model.categorymodel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class categoryAdapter  extends FirebaseRecyclerAdapter<categorymodel, categoryAdapter.catviewHolder> {

    public categoryAdapter(@NonNull FirebaseRecyclerOptions<categorymodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull catviewHolder holder, int position, @NonNull categorymodel model) {
        holder.textViewCategory.setText(model.getCat_name());
        Glide.with(holder.textViewCategory.getContext())
                .load(model.getCat_pic()).into(holder.imageViewCategory);
    }

    @NonNull
    @Override
    public catviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.row_category, parent, false);
        catviewHolder viewHolder = new catviewHolder(photoView);
        return viewHolder;

    }

    public class catviewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCategory;
        TextView textViewCategory;
        public catviewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCategory = itemView.findViewById(R.id.catimg);
            textViewCategory = itemView.findViewById(R.id.titlerow);
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
