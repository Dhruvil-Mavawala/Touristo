package com.example.tourttavels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class delete_category extends AppCompatActivity {
    Button deletecat;
    EditText tittlecat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_category);

        deletecat = findViewById(R.id.deletecat);
        tittlecat = findViewById(R.id.tittlecat);

        deletecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = tittlecat.getText().toString().trim();

                if (location.isEmpty()) {
                    Toast.makeText(delete_category.this, "Please enter a Category", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("category");
                Query query = databaseReference.orderByChild("cat_name").equalTo(location);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                snapshot.getRef().removeValue()
                                        .addOnSuccessListener(aVoid -> {
                                            // Optionally notify the user of successful deletion
                                            Toast.makeText(delete_category.this, "Package deleted successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(delete_category.this, admincategory.class);
                                            startActivity(intent);
                                            finish();
                                        })
                                        .addOnFailureListener(e -> {
                                            // Handle the error
                                            Toast.makeText(delete_category.this, "Failed to delete package", Toast.LENGTH_SHORT).show();
                                        });
                            }
                        } else {
                            Toast.makeText(delete_category.this, "No matching package found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle possible errors
                        Toast.makeText(delete_category.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}