package com.example.tourttavels;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Adapter.UserAdapter;
import com.example.tourttavels.Adapter.popularAdapter;
import com.example.tourttavels.Model.UserModel;
import com.example.tourttavels.Model.popularmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class adminuser extends AppCompatActivity {
    RecyclerView cycleuser;
    UserAdapter adapter;
    LinearLayout mainuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminuser);

        cycleuser=findViewById(R.id.cycleuser);
        mainuser=findViewById(R.id.mainuser);

        Query query= FirebaseDatabase.getInstance().getReference().child("user");

        FirebaseRecyclerOptions<UserModel> options=
                new FirebaseRecyclerOptions.Builder<UserModel>()
                        .setQuery(query,UserModel.class)
                        .build();

        adapter = new UserAdapter(options,this);
        cycleuser.setLayoutManager(new LinearLayoutManager(this));
        cycleuser.setAdapter(adapter);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainuser), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
}