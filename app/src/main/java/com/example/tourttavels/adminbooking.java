package com.example.tourttavels;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Adapter.BookAdapter;
import com.example.tourttavels.Adapter.BookAdapter2;
import com.example.tourttavels.Adapter.UserAdapter;
import com.example.tourttavels.Model.BookModel;
import com.example.tourttavels.Model.UserModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class adminbooking extends AppCompatActivity {
    RecyclerView cyclebook;
    BookAdapter2 adapter;
    LinearLayout mainbook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminbooking);

        cyclebook=findViewById(R.id.recycleBooking);
        mainbook=findViewById(R.id.mainbook);

        Query query= FirebaseDatabase.getInstance().getReference().child("booking");

        FirebaseRecyclerOptions<BookModel> options=
                new FirebaseRecyclerOptions.Builder<BookModel>()
                        .setQuery(query,BookModel.class)
                        .build();

        adapter = new BookAdapter2(options,this);
        cyclebook.setLayoutManager(new LinearLayoutManager(this));
        cyclebook.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainbook), (v, insets) -> {
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