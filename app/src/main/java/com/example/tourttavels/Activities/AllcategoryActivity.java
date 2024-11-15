package com.example.tourttavels.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Adapter.categoryAdapter;
import com.example.tourttavels.Adapter.categoryAdapter1;
import com.example.tourttavels.Adapter.popularAdapter;
import com.example.tourttavels.Model.categorymodel;
import com.example.tourttavels.Model.popularmodel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AllcategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    categoryAdapter1 categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_allcategory);

        recyclerView=findViewById(R.id.recyclecategory);


        Query query= FirebaseDatabase.getInstance().getReference().child("category");
        FirebaseRecyclerOptions<categorymodel> options=new FirebaseRecyclerOptions.Builder<categorymodel>()
                .setQuery(query, categorymodel.class)
                .build();

        categoryAdapter=new categoryAdapter1(options);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(categoryAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        categoryAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        categoryAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        categoryAdapter.notifyDataSetChanged();
    }
}