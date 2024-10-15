package com.example.tourttavels.User_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;

import com.example.tourttavels.Activities.AllProductActivity;
import com.example.tourttavels.Adapter.categoryAdapter;
import com.example.tourttavels.Model.popularmodel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tourttavels.Adapter.popularAdapter;
import com.example.tourttavels.Model.categorymodel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HomeFragment extends Fragment {
    categoryAdapter categoryAdapter;
    popularAdapter popularAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerViewpopular=view.findViewById(R.id.view_pop);
        RecyclerView recyclerViewcategory=view.findViewById(R.id.view_cat);
        TextView seeallpop=view.findViewById(R.id.seealltv);

        seeallpop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AllProductActivity.class);
                startActivity(intent);
            }
        });

        Query query= FirebaseDatabase.getInstance().getReference().child("category");
        FirebaseRecyclerOptions<categorymodel> options=new FirebaseRecyclerOptions.Builder<categorymodel>()
                .setQuery(query,categorymodel.class)
                .build();

        categoryAdapter=new categoryAdapter(options);
        recyclerViewcategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewcategory.setAdapter(categoryAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("pack");

        Query query1 = databaseReference.orderByChild("popular").equalTo(true);
        FirebaseRecyclerOptions<popularmodel> option=new FirebaseRecyclerOptions.Builder<popularmodel>()
                .setQuery(query1,popularmodel.class)
                .build();

        popularAdapter=new popularAdapter(option);
        recyclerViewpopular.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewpopular.setAdapter(popularAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        categoryAdapter.startListening();
        popularAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        categoryAdapter.stopListening();
        popularAdapter.stopListening();

    }


}