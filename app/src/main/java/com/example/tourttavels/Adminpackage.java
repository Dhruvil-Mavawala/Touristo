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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Adapter.popularAdapter;
import com.example.tourttavels.Adapter.popularAdapter2;
import com.example.tourttavels.Model.popularmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Adminpackage extends AppCompatActivity {
    popularAdapter2 adapter;
    RecyclerView recycler;
    FloatingActionButton btnfab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminpackage);

        recycler = findViewById(R.id.rcv1);
        btnfab=findViewById(R.id.fab);



        btnfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Adminpackage.this, "Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Adminpackage.this,add_package.class));
            }
        });

        Query query= FirebaseDatabase.getInstance().getReference().child("pack");

        FirebaseRecyclerOptions<popularmodel> options=
                new FirebaseRecyclerOptions.Builder<popularmodel>()
                        .setQuery(query,popularmodel.class)
                        .build();

        adapter = new popularAdapter2(options, this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);


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