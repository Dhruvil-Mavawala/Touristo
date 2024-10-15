package com.example.tourttavels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tourttavels.Activities.Home;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class add_package extends AppCompatActivity {
    EditText edtittle,edlocation,edprice,edDesc,edpic,edduration,edbed,edwifi,edpopualr,edguide,edscore;
    String tittle,location,price,Desc,pic,duration,bed,wifi,popualr,guide,score;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_package);

        edtittle=findViewById(R.id.edtittle);
        edlocation=findViewById(R.id.edlocation);
        edDesc=findViewById(R.id.edDesc);
        edpic=findViewById(R.id.edpic);
        edduration=findViewById(R.id.edduration);
        edbed=findViewById(R.id.edbed);
        edguide=findViewById(R.id.edguide);
        edscore=findViewById(R.id.edscore);
        edwifi=findViewById(R.id.edwifi);
        edpopualr=findViewById(R.id.edpopualr);
        edprice=findViewById(R.id.edprice);
        btnInsert=findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tittle=edtittle.getText().toString();
                location=edlocation.getText().toString();
                price=edprice.getText().toString();
                Desc=edDesc.getText().toString();
                guide=edguide.getText().toString();
                score=edscore.getText().toString();
                pic=edpic.getText().toString();
                duration=edduration.getText().toString();
                bed=edbed.getText().toString();
                wifi=edwifi.getText().toString();
                popualr=edpopualr.getText().toString();

                if(tittle.isEmpty()||location.isEmpty()||price.isEmpty()||Desc.isEmpty()||pic.isEmpty()||duration.isEmpty()||bed.isEmpty()||wifi.isEmpty()||popualr.isEmpty()){
                    Toast.makeText(add_package.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(add_package.this, "Product Added", Toast.LENGTH_SHORT).show();

                    DatabaseReference dbrefs=FirebaseDatabase.getInstance().getReference().child("pack").push();
                    dbrefs.child("tittle").setValue(tittle);
                    dbrefs.child("location").setValue(location);
                    dbrefs.child("price").setValue(price);
                    dbrefs.child("description").setValue(Desc);
                    dbrefs.child("pic").setValue(pic);
                    dbrefs.child("guide").setValue(guide);
                    dbrefs.child("score").setValue(score);
                    dbrefs.child("popualr").setValue(popualr);
                    dbrefs.child("duration").setValue(duration);
                    dbrefs.child("bed").setValue(bed);
                    dbrefs.child("wifi").setValue(wifi);

                    Intent intent=new Intent(add_package.this, Adminpackage.class);
                    startActivity(intent);
                }

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
//    public void insertData(){
//        Map<String,Object> map=new HashMap<>();
//        map.put(Constantdata.KEY_NAME,etnameadd.getText().toString());
//        map.put(Constantdata.KEY_COURSE,etcourseadd.getText().toString());
//        map.put(Constantdata.KEY_EMAIL,etemailadd.getText().toString());
//        map.put(Constantdata.KEY_MOBILE,etmobadd.getText().toString());
//        map.put(Constantdata.KEY_PIC,etpicadd.getText().toString());
//
//        FirebaseDatabase.getInstance().getReference().child("Person")
//                .push()
//                .setValue(map)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Snackbar.make(main,"Successfully Inserted",Snackbar.LENGTH_SHORT).show();
//                        Intent intent=new Intent(insert.this,MainActivity.class);
//                        startActivity(intent);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(insert.this, "Something Went Wrong...", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
}