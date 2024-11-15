package com.example.tourttavels.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tourttavels.Model.popularmodel;
import com.example.tourttavels.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class popularAdapter2 extends FirebaseRecyclerAdapter<popularmodel, popularAdapter2.popularViewHolder> {
    private String selectedCname = "";
    private List<String> catnames = new ArrayList<>();
    private Spinner upopular, ucategory;
    private final Context context;

    public popularAdapter2(@NonNull FirebaseRecyclerOptions<popularmodel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull popularViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull popularmodel model) {
        holder.tittle.setText(model.getTittle());
        holder.location.setText(model.getLocation());
        holder.score.setText(model.getScore());
        Glide.with(holder.picimg.getContext()).load(model.getPic()).into(holder.picimg);

        holder.btndelete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false)
                    .setTitle("Are you sure you want to delete?")
                    .setMessage("Delete can't be undone!")
                    .setPositiveButton("Delete", (dialogInterface, i) -> {
                        FirebaseDatabase.getInstance().getReference().child("pack")
                                .child(getRef(position).getKey()).removeValue()
                                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show())
                                .addOnFailureListener(e -> Toast.makeText(context, "Deletion failed", Toast.LENGTH_SHORT).show());
                    })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show())
                    .show();
        });

        holder.btnupdate.setOnClickListener(view -> {
            DialogPlus dialogPlus = DialogPlus.newDialog(holder.btnupdate.getContext())
                    .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.dialog_update))
                    .setExpanded(true, 1800)
                    .create();

            View myview = dialogPlus.getHolderView();
            EditText utittle = myview.findViewById(R.id.utittle);
            EditText upic = myview.findViewById(R.id.upic);
            EditText uloc = myview.findViewById(R.id.ulocation);
            EditText uprice = myview.findViewById(R.id.uprice);
            EditText uscore = myview.findViewById(R.id.uscore);
            EditText uduration = myview.findViewById(R.id.uduration);
            EditText uwifi = myview.findViewById(R.id.uwifi);
            EditText ubed = myview.findViewById(R.id.ubed);
            EditText uguide = myview.findViewById(R.id.uguide);
            EditText udesc = myview.findViewById(R.id.uDesc);
            Button btnupdatedetails = myview.findViewById(R.id.btnupdatedetail);
            ucategory = myview.findViewById(R.id.ucategory);
            upopular = myview.findViewById(R.id.upopualr);

            fetchCategory();

            ucategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedCname = catnames.get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Optional: Handle case where no item is selected if necessary
                }
            });

            // Set existing data
            utittle.setText(model.getTittle());
            uloc.setText(model.getLocation());
            uscore.setText(model.getScore());
            udesc.setText(model.getDescription()); // Corrected description
            upic.setText(model.getPic());
            uprice.setText(model.getPrice());
            uduration.setText(model.getDuration());
            uwifi.setText(model.getWifi());
            ubed.setText(model.getBed());
            uguide.setText(model.getGuide());

            // Create adapter for popularity choices
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, // Changed 'this' to 'context'
                    R.array.popular_choices, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            upopular.setAdapter(adapter);

            btnupdatedetails.setOnClickListener(v -> {
                String selectedPopular = upopular.getSelectedItem().toString();
                boolean popular = selectedPopular.equals("true"); // Changed to boolean

                Map<String, Object> map = new HashMap<>();
                map.put("tittle", utittle.getText().toString());
                map.put("location", uloc.getText().toString());
                map.put("score", uscore.getText().toString());
                map.put("description", udesc.getText().toString());
                map.put("pic", upic.getText().toString());
                map.put("price", uprice.getText().toString());
                map.put("duration", uduration.getText().toString());
                map.put("wifi", uwifi.getText().toString());
                map.put("bed", ubed.getText().toString());
                map.put("guide", uguide.getText().toString());
                map.put("category", ucategory.getSelectedItem().toString());
                map.put("popular", popular); // Correctly assigning boolean value

                FirebaseDatabase.getInstance().getReference().child("pack")
                        .child(getRef(position).getKey()).updateChildren(map)
                        .addOnSuccessListener(aVoid -> Toast.makeText(holder.btnupdate.getContext(), "Updated successfully", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(holder.btnupdate.getContext(), "Update failed", Toast.LENGTH_SHORT).show());

                dialogPlus.dismiss();
            });
            dialogPlus.show();
        });
    }

    @NonNull
    @Override
    public popularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_popular_admin, parent, false);
        return new popularViewHolder(view);
    }

    class popularViewHolder extends RecyclerView.ViewHolder {
        TextView tittle, location, btnupdate, btndelete, score;
        ImageView picimg;

        public popularViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.tittletxt);
            location = itemView.findViewById(R.id.locationtxt);
            picimg = itemView.findViewById(R.id.picimg);
            score = itemView.findViewById(R.id.scoretxt);
            btnupdate = itemView.findViewById(R.id.btnupdate);
            btndelete = itemView.findViewById(R.id.btndelete);
        }
    }

    public void fetchCategory() {
        DatabaseReference dbRefs = FirebaseDatabase.getInstance().getReference().child("category");

        dbRefs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    catnames.clear();
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        String cname = childSnapshot.child("cat_name").getValue(String.class);
                        catnames.add(cname);
                    }

                    // Update the Spinner adapter after fetching categories
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, catnames); // Corrected context
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ucategory.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
            }
        });
    }
}
