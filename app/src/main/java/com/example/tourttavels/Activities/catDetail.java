package com.example.tourttavels.Activities;

import static java.security.AccessController.getContext;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Adapter.popularAdapter;
import com.example.tourttavels.Model.popularmodel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class catDetail extends AppCompatActivity {
    RecyclerView rcvcatdetail;
    popularAdapter popularAdapter;

    String cat_name, cat_pic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cat_detail);
        rcvcatdetail=findViewById(R.id.rcvcatdetail);

        cat_name=getIntent().getStringExtra("cat_name");
        cat_pic=getIntent().getStringExtra("cat_pic");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("pack");

        Query query1 = databaseReference.orderByChild("category").equalTo(cat_name);
        FirebaseRecyclerOptions<popularmodel> option=new FirebaseRecyclerOptions.Builder<popularmodel>()
                .setQuery(query1,popularmodel.class)
                .build();

        popularAdapter=new popularAdapter(option);
        rcvcatdetail.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        rcvcatdetail.setAdapter(popularAdapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        popularAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        popularAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        popularAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        popularAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        popularAdapter.notifyDataSetChanged();
    }
}