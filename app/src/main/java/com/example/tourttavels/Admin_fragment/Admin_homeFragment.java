package com.example.tourttavels.Admin_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourttavels.Activities.Home;
import com.example.tourttavels.Adminpackage;
import com.example.tourttavels.R;
import com.example.tourttavels.adminbooking;
import com.example.tourttavels.admincategory;
import com.example.tourttavels.adminuser;

public class Admin_homeFragment extends Fragment {
    CardView cvpackage, cvuser, cvcategory,cvbooking,cvrevanue;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Admin_homeFragment() {
        // Required empty public constructor
    }

    public static Admin_homeFragment newInstance(String param1, String param2) {
        Admin_homeFragment fragment = new Admin_homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_admin_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Admin Home");
        cvpackage=view.findViewById(R.id.cvpackage);
        cvuser=view.findViewById(R.id.cvuser);
        cvcategory=view.findViewById(R.id.cvcategory);
        cvbooking=view.findViewById(R.id.cvbooking);
        cvrevanue=view.findViewById(R.id.cvrevenue);

        cvpackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Adminpackage.class);
                startActivity(intent);
            }
        });
        cvcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), admincategory.class);
                startActivity(intent);
            }
        });
        cvuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), adminuser.class));
            }
        });
        cvbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), adminbooking.class));
            }
        });


    }
}