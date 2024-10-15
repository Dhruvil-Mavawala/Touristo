package com.example.tourttavels.User_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourttavels.Activities.login;
import com.example.tourttavels.Constantdata;
import com.example.tourttavels.R;
import com.example.tourttavels.pinfo;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class ProfileFragment extends Fragment {

    ImageView image1;
    TextView camera;
    TextView tvname;
    TextView nameedit;
    View view;
    Button btnlogout;
    CardView cv_personal,cv_aboutus,cv_help,cv_version;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        image1=view.findViewById(R.id.image1);
        camera = view.findViewById(R.id.camera);
        btnlogout = view.findViewById(R.id.btnlogout);
        cv_personal = view.findViewById(R.id.cv_personal);
        cv_aboutus = view.findViewById(R.id.cv_aboutus);
        cv_help = view.findViewById(R.id.cv_help);
        cv_version = view.findViewById(R.id.cv_version);
        nameedit = view.findViewById(R.id.nameedit);
        tvname = view.findViewById(R.id.tvname);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constantdata.SP_LOGIN, Context.MODE_PRIVATE);
        String username=sharedPreferences.getString(Constantdata.SP_USERNAME,"");

        tvname.setText(username);



        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getActivity(), login.class);
                startActivity(i1);
                SharedPreferences.Editor ed = sharedPreferences.edit();
                ed.clear();
                ed.apply();
            }
        });

        cv_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(getActivity())
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
        CardView cv_aboutus = view.findViewById(R.id.cv_aboutus);
        cv_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = android.net.Uri.parse("https://touristo-travel.netlify.app/homepage");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });
        CardView cv_version=view.findViewById(R.id.cv_version);
        cv_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "App is Up to Date ", Toast.LENGTH_SHORT).show();
            }
        });
        CardView cv_personal=view.findViewById(R.id.cv_personal);
        cv_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), pinfo.class));
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        image1.setImageURI(uri);
    }
}