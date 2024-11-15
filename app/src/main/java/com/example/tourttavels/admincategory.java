package com.example.tourttavels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Adapter.categoryAdapter;
import com.example.tourttavels.Adapter.categoryAdapter2;
import com.example.tourttavels.Model.categorymodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class admincategory extends AppCompatActivity {
    FloatingActionButton fabdeletecat;
    FloatingActionButton fabcat;
    RecyclerView rcvcat;
    categoryAdapter2 adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admincategory);

        rcvcat=findViewById(R.id.rcvcatla);
        fabcat=findViewById(R.id.fabcatla);
        fabdeletecat=findViewById(R.id.fabdeletecatla);

        fabcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(admincategory.this, "Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(admincategory.this,add_category.class));
            }
        });

        fabdeletecat.setOnClickListener(view -> {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,delete_category.class));
            finish();
        });
        Query query= FirebaseDatabase.getInstance().getReference().child("category");

        FirebaseRecyclerOptions<categorymodel> options=
                new FirebaseRecyclerOptions.Builder<categorymodel>()
                        .setQuery(query,categorymodel.class)
                        .build();

        adapter = new categoryAdapter2(options);
        rcvcat.setLayoutManager(new GridLayoutManager(this,2));
        rcvcat.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.notifyDataSetChanged();
    }
}