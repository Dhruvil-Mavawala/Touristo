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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_category extends AppCompatActivity {
    EditText cattitle,catpic;
    String title,pic;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_category);
        cattitle=findViewById(R.id.cattitle);
        catpic=findViewById(R.id.catpic);
        btnInsert=findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=cattitle.getText().toString();
                pic=catpic.getText().toString();
                if (title.isEmpty()||pic.isEmpty()){
                    Toast.makeText(add_category.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(add_category.this, "Category Added", Toast.LENGTH_SHORT).show();

                    DatabaseReference dbrefs= FirebaseDatabase.getInstance().getReference().child("category").push();
                    dbrefs.child("cat_name").setValue(title);
                    dbrefs.child("cat_pic").setValue(pic);

                    Intent intent=new Intent(add_category.this, admincategory.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}