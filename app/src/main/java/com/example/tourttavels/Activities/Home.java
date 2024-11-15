package com.example.tourttavels.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Constantdata;
import com.example.tourttavels.User_fragment.BookingFragment;
import com.example.tourttavels.User_fragment.HomeFragment;
import com.example.tourttavels.User_fragment.ProfileFragment;
import com.example.tourttavels.R;
import com.example.tourttavels.searchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {

    public RecyclerView.Adapter adapterpopular,adaptercat;
    public RecyclerView recyclerViewpopular,recyclerViewcategory;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    NavigationView nav_view;
    DrawerLayout Draw_main;
    CircleImageView headerImage;
    TextView tvname,tvemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        bottomNavigationView=findViewById(R.id.bottomBar);
        Draw_main = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        nav_view = findViewById(R.id.nav_view);

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
                } else if (item.getItemId() == R.id.nav_cc) {

                }
                return true;
            }
        });

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle =new ActionBarDrawerToggle(this,Draw_main,toolbar,R.string.nav_open,R.string.nav_close);

        Draw_main.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home)
                {
                    openFragment(new HomeFragment());
                }
                else if (item.getItemId()==R.id.nav_about)
                {
                    Uri uri = android.net.Uri.parse("https://touristo-travel.netlify.app/aboutus");
                    startActivity(new Intent(Intent.ACTION_VIEW,uri));
                    //openFragment(new CategoryFragment());

                }
                else if (item.getItemId()==R.id.nav_cc)
                {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+918460512338"));
                    startActivity(intent);
                }
                else if (item.getItemId()==R.id.nav_share)
                {
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(intent.EXTRA_SUBJECT,"Check out this Tours and Travels Application");
                    intent.putExtra(intent.EXTRA_TEXT,"https://touristo-travel.netlify.app/download");
                    startActivity(Intent.createChooser(intent,"Share Via"));

                }
                else if (item.getItemId()==R.id.nav_rating)
                {
                    showRatingDialog();
                    //openFragment(new ProductFragment());

                }
                Draw_main.close();
                return true;
            }


        });

        View headerView = nav_view.getHeaderView(0); // 0 is the index of the header


        headerImage = headerView.findViewById(R.id.profile_image);
        tvname = headerView.findViewById(R.id.tvname);
        tvemail = headerView.findViewById(R.id.tvemail);


        SharedPreferences sharedPreferences = Home.this.getSharedPreferences(Constantdata.SP_LOGIN, Context.MODE_PRIVATE);
        String email=sharedPreferences.getString(Constantdata.SP_EMAIL,"");
        String username=sharedPreferences.getString(Constantdata.SP_FULLNAME,"");

        tvname.setText(username);
        tvemail.setText(email);

        SharedPreferences sp=getSharedPreferences(Constantdata.SP_LOGIN,MODE_PRIVATE);
        String pic=sp.getString(Constantdata.KEY_PIC,"");

        loadImageFromPath(pic);




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
        fragmentTransaction.replace(R.id.frame,fragment,null);
        fragmentTransaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void openFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame,fragment);
        fragmentTransaction.commit();

    }
    private void showRatingDialog() {
        // Inflate the dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_rating, null);

        // Create AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle("Rate Us ⭐");

        // Initialize RatingBar and Button
        RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);
        Button submitRating = dialogView.findViewById(R.id.submitRating);

        AlertDialog dialog = builder.create();


        // Set up the button click listener
        submitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                Toast.makeText(Home.this, "You rated: " + rating + " stars", Toast.LENGTH_SHORT).show();
                // dismiss alert dialog
                dialog.dismiss();
                // Handle the submitted rating as needed (e.g., save to a database)
            }
        });

        // Create the dialog

        // Show the dialog
        dialog.show();

        // Optional: dismiss the dialog when clicking outside of it
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // Any action on dialog cancel
            }
        });
    }


    private void loadImageFromPath(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            // Decode the image file into a Bitmap
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

            // Set the Bitmap to the ImageView
            headerImage.setImageBitmap(bitmap);

        } else {
            // Handle the case where the file does not exist
            headerImage.setImageResource(R.mipmap.profile); // Set a default image or handle error
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