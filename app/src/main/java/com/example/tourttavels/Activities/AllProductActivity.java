package com.example.tourttavels.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Adapter.categoryAdapter;
import com.example.tourttavels.Adapter.popularAdapter;
import com.example.tourttavels.Model.categorymodel;
import com.example.tourttavels.Model.popularmodel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AllProductActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    popularAdapter popularAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_product);

        recyclerView=findViewById(R.id.Recyclerpack);


        Query query= FirebaseDatabase.getInstance().getReference().child("pack");
        FirebaseRecyclerOptions<popularmodel> options=new FirebaseRecyclerOptions.Builder<popularmodel>()
                .setQuery(query, popularmodel.class)
                .build();

        popularAdapter=new popularAdapter(options);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(popularAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        popularAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        popularAdapter.stopListening();

    }


}