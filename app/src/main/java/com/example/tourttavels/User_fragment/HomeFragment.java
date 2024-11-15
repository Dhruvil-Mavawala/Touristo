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
import com.example.tourttavels.Activities.AllcategoryActivity;
import com.example.tourttavels.Adapter.categoryAdapter;
import com.example.tourttavels.Model.popularmodel;
import com.example.tourttavels.R;
import com.example.tourttavels.admincategory;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.tourttavels.Adapter.popularAdapter;
import com.example.tourttavels.Model.categorymodel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HomeFragment extends Fragment {
    TextView seeallcat;
    categoryAdapter categoryAdapter;
    popularAdapter popularAdapter;

    ViewFlipper vf1;

    int img[] = {R.drawable.banner4};


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
        seeallcat=view.findViewById(R.id.seeallcat);

        vf1 =view.findViewById(R.id.vf);

        for(int i = 0; i<=img.length-1 ; i++)
        {
            dispimg(img[i]);
        }

        seeallcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AllcategoryActivity.class));
            }
        });

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
    void dispimg(int i) {
        ImageView i1 = new ImageView(getContext());
        i1.setImageResource(i);
        vf1.addView(i1);
        vf1.setFlipInterval(3000);
        vf1.setAutoStart(true);

        // Set animations for flipping
        vf1.setInAnimation(getContext(),R.anim.slide_in_right);
        vf1.setOutAnimation(getContext(),R.anim.slide_out_left);
    }


    @Override
    public void onStart() {
        super.onStart();
        popularAdapter.startListening();
        categoryAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        popularAdapter.startListening();
        categoryAdapter.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        popularAdapter.startListening();
        categoryAdapter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        popularAdapter.startListening();
        categoryAdapter.startListening();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        popularAdapter.startListening();
        categoryAdapter.startListening();
    }
}