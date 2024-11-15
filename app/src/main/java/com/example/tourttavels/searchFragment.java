package com.example.tourttavels;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourttavels.Adapter.popularAdapter;
import com.example.tourttavels.Model.popularmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class searchFragment extends Fragment {
    private RecyclerView cycle;
    private popularAdapter adapter;
    private SearchView searchView;

    public searchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cycle = view.findViewById(R.id.cycle);
        searchView = view.findViewById(R.id.searchView);

        setupRecyclerView(""); // Initially load all items

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setupRecyclerView(query); // Filter based on search query
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                setupRecyclerView(newText); // Filter as user types
                return false;
            }
        });
    }

    private void setupRecyclerView(String queryText) {
        Query query;
        if (queryText.isEmpty()) {
            query = FirebaseDatabase.getInstance().getReference().child("pack");
        } else {
            Log.d("SearchFragment", "Query Text: " + queryText);
            query = FirebaseDatabase.getInstance().getReference().child("pack")
                    .orderByChild("location") // Ensure this field exists in your database
                    .startAt(queryText)
                    .endAt(queryText + "\uf8ff");
            //show capital and


        }

        FirebaseRecyclerOptions<popularmodel> options =
                new FirebaseRecyclerOptions.Builder<popularmodel>()
                        .setQuery(query, popularmodel.class)
                        .build();

        adapter = new popularAdapter(options);
        cycle.setLayoutManager(new LinearLayoutManager(getContext()));
        cycle.setAdapter(adapter);
        adapter.startListening(); // Start listening to the adapter to show updated results
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.notifyDataSetChanged();
    }

}
