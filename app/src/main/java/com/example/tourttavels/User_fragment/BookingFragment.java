package com.example.tourttavels.User_fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourttavels.Adapter.BookAdapter;
import com.example.tourttavels.Constantdata;
import com.example.tourttavels.Model.BookModel;
import com.example.tourttavels.Model.categorymodel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class BookingFragment extends Fragment {
    BookAdapter bookAdapter;
    RecyclerView recyclerView;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
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
        view= inflater.inflate(R.layout.fragment_booking, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.rcv);


        SharedPreferences sp=getActivity().getSharedPreferences(Constantdata.SP_LOGIN,MODE_PRIVATE);
        String username=sp.getString(Constantdata.SP_USERNAME,"");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("booking");

        Query query = databaseReference.orderByChild("username").equalTo(username);
        FirebaseRecyclerOptions<BookModel> options=new FirebaseRecyclerOptions.Builder<BookModel>()
                .setQuery(query,BookModel.class)
                .build();

        bookAdapter=new BookAdapter(options,getContext());
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));
        recyclerView.setAdapter(bookAdapter);

    }
    @Override
    public void onStart() {
        super.onStart();
        bookAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        bookAdapter.stopListening();

    }
}