package com.example.tourttavels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.tourttavels.Activities.BookActivity;
import com.example.tourttavels.Model.popularmodel;

import java.io.Serializable;

public class PackDetail extends AppCompatActivity {

    TextView tvtitle,tvlocation,tvprice,tvbed,tvday,tvguide,tvwifi,tvdesc;
    Button btnBook;
    popularmodel model;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pack_detail);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvtitle=findViewById(R.id.tvtitle);
        tvlocation=findViewById(R.id.tvlocation);
        tvdesc=findViewById(R.id.tvdesc);
        tvprice=findViewById(R.id.tvprice);
        tvbed=findViewById(R.id.tvbed);
        tvday=findViewById(R.id.tvday);
        tvguide=findViewById(R.id.tvguide);
        tvwifi=findViewById(R.id.tvwifi);
        btnBook=findViewById(R.id.btnBook);
        image=findViewById(R.id.imagepack);



        Intent intent = getIntent();
        Serializable serializableObject = intent.getSerializableExtra("pack");

        if (serializableObject instanceof popularmodel) {
             model = (popularmodel) serializableObject;

            btnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(PackDetail.this, BookActivity.class);
                    intent.putExtra("pack",model);
                    startActivity(intent);

                }
            });
            // Now you can use the model object
        } else {
            // Handle the case where the object is not of the expected type
        }


        tvtitle.setText(model.getTittle());
        tvlocation.setText(model.getLocation());
        tvprice.setText(model.getPrice());
        tvbed.setText(model.getBed()+"Bed");
        tvday.setText(model.getDuration());
        tvguide.setText(model.getGuide());
        tvwifi.setText(model.getWifi());
        tvdesc.setText(model.getDescription());
        //image.setImageResource(getResources().getIdentifier(model.getPic(),"drawable",getPackageName()));
        Glide.with(this)
                .load(model.getPic())
                .into(image);


    }
}