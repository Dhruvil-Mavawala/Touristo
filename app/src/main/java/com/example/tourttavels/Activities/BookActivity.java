package com.example.tourttavels.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.tourttavels.Constantdata;
import com.example.tourttavels.Model.BookModel;
import com.example.tourttavels.Model.popularmodel;
import com.example.tourttavels.PackDetail;
import com.example.tourttavels.R;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class BookActivity extends AppCompatActivity {

    Button btnBooking;
    popularmodel model;
    String pname,pid,ppic,username,status;
    Double gst,price,total;
    TextView tvprice,tvgst,tvtotal,tvlocation;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvprice=findViewById(R.id.tvprice);
        tvgst=findViewById(R.id.tvgst);
        tvtotal=findViewById(R.id.tvtotal);
        tvlocation=findViewById(R.id.tvloc);
        image=findViewById(R.id.imagepic);

        SharedPreferences sp=getSharedPreferences(Constantdata.SP_LOGIN,MODE_PRIVATE);
        username=sp.getString(Constantdata.SP_USERNAME,"");

        Intent intent = getIntent();
        Serializable serializableObject = intent.getSerializableExtra("pack");
        String tvdate=intent.getStringExtra("date");

        if (serializableObject instanceof popularmodel) {
             model = (popularmodel) serializableObject;
             pname=model.getLocation();
             price=Double.parseDouble(model.getPrice());
             gst=price*0.05;
             total=price+gst;
             pid=model.getPic();
             ppic=model.getPic();
             status= "pending";


            // Now you can use the model object
        } else {
            // Handle the case where the object is not of the expected type
        }

        tvprice.setText(price+"/-");
        tvgst.setText(gst+"/-");
        tvtotal.setText(total+"/-");
        tvlocation.setText(pname);
        Glide.with(this)
                .load(ppic)
                .into(image);


        btnBooking=findViewById(R.id.btnBooking);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookModel bookModel=new BookModel(gst+"",pid+"",pname+"",ppic+"",price+"",status+"",total+"",username+"",tvdate+"");
                FirebaseDatabase.getInstance().getReference().child("booking").push().setValue(bookModel);

                    Intent i=new Intent(BookActivity.this,SuccessActivity.class);
                    startActivity(i);
                    finish();
            }
        });
    }
}