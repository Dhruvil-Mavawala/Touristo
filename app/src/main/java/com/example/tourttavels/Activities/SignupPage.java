package com.example.tourttavels.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tourttavels.Model.UserModel;
import com.example.tourttavels.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupPage extends AppCompatActivity {
    EditText username,fullname,password,con_password,email,phone;
    Button register_button;
    TextView back;
    TextView login_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);

        username=findViewById(R.id.username);
        fullname=findViewById(R.id.fullname);
        password=findViewById(R.id.password);
        con_password=findViewById(R.id.confirm_password);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        register_button=findViewById(R.id.register_button);
        login_link=findViewById(R.id.login_link);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupPage.this, login.class));
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String strusername=username.getText().toString();
                String strfullname=fullname.getText().toString();
                String strpassword=password.getText().toString();
                String strcon_password=con_password.getText().toString();
                String stremail=email.getText().toString();
                String strphone=phone.getText().toString();

                if (strusername.contains("@")){
                    Toast.makeText(SignupPage.this, "Doesn't use @ in username", Toast.LENGTH_SHORT).show();
                }

                else if (strphone.length()!=10){
                    Toast.makeText(SignupPage.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }


                else if (!stremail.contains("@")){
                    Toast.makeText(SignupPage.this, "Please Enter the Email address properly !!", Toast.LENGTH_SHORT).show();
                }

                else if (strusername.isEmpty()||strfullname.isEmpty()||strpassword.isEmpty()||strcon_password.isEmpty()||stremail.isEmpty()||strphone.isEmpty()){
                    Toast.makeText(SignupPage.this, "Please fill all fields...", Toast.LENGTH_SHORT).show();
                }
                else if (!strpassword.equals(strcon_password)){
                    Toast.makeText(SignupPage.this, "Password not match...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    UserModel usermdl = new UserModel(stremail, strpassword, strphone, strusername, strfullname);
                    HashMap<String, Object> map = UserModel.convertomap(usermdl);

                    FirebaseDatabase.getInstance().getReference().child("user").push().setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    username.setText("");
                                    fullname.setText("");
                                    password.setText("");
                                    con_password.setText("");
                                    email.setText("");
                                    phone.setText("");
                                    Toast.makeText(SignupPage.this, "Register Successfully...", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    username.setText("");
                                    fullname.setText("");
                                    password.setText("");
                                    con_password.setText("");
                                    email.setText("");
                                    phone.setText("");
                                    Toast.makeText(SignupPage.this, "Failed...", Toast.LENGTH_SHORT).show();
                                }
                            });


                }
            }
        });

        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupPage.this, login.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}