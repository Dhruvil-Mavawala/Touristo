package com.example.tourttavels.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.User_fragment.BookingFragment;
import com.example.tourttavels.User_fragment.HomeFragment;
import com.example.tourttavels.User_fragment.ProfileFragment;
import com.example.tourttavels.R;
import com.example.tourttavels.searchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    public RecyclerView.Adapter adapterpopular,adaptercat;
    public RecyclerView recyclerViewpopular,recyclerViewcategory;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        bottomNavigationView=findViewById(R.id.bottomBar);

        replaceFragment(new HomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    replaceFragment(new HomeFragment());
                }
                else if(item.getItemId() == R.id.search){
                    replaceFragment(new searchFragment());
                    }
                else if(item.getItemId() == R.id.like){
                    replaceFragment(new BookingFragment());
                }
                else if(item.getItemId() == R.id.profile){
                    replaceFragment(new ProfileFragment());
                }
            return true;
            }
        });



//        SharedPreferences sharedPreferences=getSharedPreferences(Constantdata.SP_LOGIN,MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        String strusername= sharedPreferences.getString(Constantdata.SP_USERNAME,"");
//        String strfullname= sharedPreferences.getString(Constantdata.SP_FULLNAME,"");
//        String stremail= sharedPreferences.getString(Constantdata.SP_EMAIL,"");
//        String strphone= sharedPreferences.getString(Constantdata.SP_PHONE,"");
//
//        TextView usernametv=findViewById(R.id.usernamehome);
//        TextView fullnametv=findViewById(R.id.fullnamehome);
//        TextView emailtv=findViewById(R.id.emailhome);
//        TextView phoneTv=findViewById(R.id.phonehome);
//        usernametv.setText(strusername);
//        fullnametv.setText(strfullname);
//        emailtv.setText(stremail);
//        phoneTv.setText(strphone);
//
//        editor.apply();
//
//        usernametv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Home.this, "Username", Toast.LENGTH_SHORT).show();
//            }
//        });
//        fullnametv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Home.this, "Full name", Toast.LENGTH_SHORT).show();
//            }
//        });
//        emailtv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Home.this, "email", Toast.LENGTH_SHORT).show();
//            }
//        });
//        phoneTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Home.this, "phone", Toast.LENGTH_SHORT).show();
//            }
//        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_activity_home,fragment,null);
        fragmentTransaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

//    private void initRecyclerview() {
////        ArrayList<popularmodel> items=new ArrayList<>();
////        items.add(new popularmodel("MAr Caible ava","Miamme beach","gfhhsdfhgdfhgfhsghfsgshfghsgfhg\ndfasfgfsa\nfafsdsfd",2,false,0.00,"pic1",false,0,"4days 3night","Beach"));
////        items.add(new popularmodel("Marine Drive","Mrine beach","adadasdsa\nsasdsa\ndfsd",2,false,0.00,"pic2",true,0,"4days 3night","Mountain"));
////        items.add(new popularmodel("MAr Caible ava","Miamme beach","gfhhsdfhgdfhgfhsghfsgshfghsgfhg\ndfasfgfsa\nfafsdsfd",2,false,0.00,"pic3",true,0,"4days 3night","Cofee"));
////
////        recyclerViewpopular=findViewById(R.id.view_pop);
////        recyclerViewpopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
////        adapterpopular=new popularAdapter(items);
////        recyclerViewpopular.setAdapter(adapterpopular);
//
//
//
//    }
}