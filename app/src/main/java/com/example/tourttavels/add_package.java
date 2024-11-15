package com.example.tourttavels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tourttavels.Adapter.categoryAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class add_package extends AppCompatActivity {
    EditText edtittle, edlocation, edprice, edDesc, edpic, edduration, edbed, edwifi, edguide, edscore;
    Spinner edpopualr,edcategory;
    String tittle, location, price, Desc, pic, duration, bed, wifi, guide, score,category;
    Boolean popualr;
    Button btnInsert;
    categoryAdapter adapter;
    String selctedCname = "";
    List<String> catnames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_package);

        edtittle = findViewById(R.id.edtittle);
        edlocation = findViewById(R.id.edlocation);
        edDesc = findViewById(R.id.edDesc);
        edpic = findViewById(R.id.edpic);
        edduration = findViewById(R.id.edduration);
        edbed = findViewById(R.id.edbed);
        edguide = findViewById(R.id.edguide);
        edscore = findViewById(R.id.edscore);
        edwifi = findViewById(R.id.edwifi);
        edpopualr = findViewById(R.id.edpopualr);
        edcategory = findViewById(R.id.edcategory);
        edprice = findViewById(R.id.edprice);
        btnInsert = findViewById(R.id.btnInsert);

        fetchCategory();

        edcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selctedCname = catnames.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Handle case where no item is selected if necessary
        }
    });

        // Set up Spinner for popular
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.popular_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edpopualr.setAdapter(adapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tittle = edtittle.getText().toString();
                location = edlocation.getText().toString();
                price = edprice.getText().toString();
                Desc = edDesc.getText().toString();
                guide = edguide.getText().toString();
                score = edscore.getText().toString();
                pic = edpic.getText().toString();
                duration = edduration.getText().toString();
                bed = edbed.getText().toString();
                wifi = edwifi.getText().toString();


                // Get selected value from Spinner
                String selectedPopular = edpopualr.getSelectedItem().toString();
                popualr = selectedPopular.equals("true");

                if (tittle.isEmpty() || location.isEmpty() || price.isEmpty() || Desc.isEmpty() || pic.isEmpty() || duration.isEmpty() || bed.isEmpty() || wifi.isEmpty()) {
                    Toast.makeText(add_package.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference dbrefs = FirebaseDatabase.getInstance().getReference().child("pack").push();
                    dbrefs.child("tittle").setValue(tittle);
                    dbrefs.child("location").setValue(location);
                    dbrefs.child("price").setValue(price);
                    dbrefs.child("description").setValue(Desc);
                    dbrefs.child("pic").setValue(pic);
                    dbrefs.child("guide").setValue(guide);
                    dbrefs.child("score").setValue(score);
                    dbrefs.child("popular").setValue(popualr);
                    dbrefs.child("duration").setValue(duration);
                    dbrefs.child("bed").setValue(bed);
                    dbrefs.child("category").setValue(selctedCname);
                    dbrefs.child("wifi").setValue(wifi)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(add_package.this, "Product Added Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(add_package.this, Adminpackage.class));
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(add_package.this, "Failed to add product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void fetchCategory() {
        DatabaseReference dbRefs = FirebaseDatabase.getInstance().getReference().child("category");

        dbRefs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    catnames.clear();
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {

                        String cname = childSnapshot.child("cat_name").getValue(String.class);
                        catnames.add(cname);
                    }

                    // Update the Spinner adapter after fetching categories
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(add_package.this, android.R.layout.simple_spinner_item, catnames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    edcategory.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });
    }
}
