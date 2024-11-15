package com.example.tourttavels;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Calendar;

public class PackDetail extends AppCompatActivity {

    TextView tvtitle, tvlocation, tvprice, tvbed, tvday, tvguide, tvwifi, tvdesc, terms;
    Button btnBook;
    popularmodel model;
    EditText tv_date;
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

        tvtitle = findViewById(R.id.tvtitle);
        tvlocation = findViewById(R.id.tvlocation);
        tvdesc = findViewById(R.id.tvdesc);
        tvprice = findViewById(R.id.tvprice);
        tvbed = findViewById(R.id.tvbed);
        tvday = findViewById(R.id.tvday);
        tvguide = findViewById(R.id.tvguide);
        tvwifi = findViewById(R.id.tvwifi);
        btnBook = findViewById(R.id.btnBook);
        image = findViewById(R.id.imagepack);
        tv_date = findViewById(R.id.date_picker_actions);
        terms = findViewById(R.id.terms);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DAY_OF_MONTH);

        tv_date.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(PackDetail.this,
                    (datePicker, selectedYear, selectedMonth, selectedDate) -> {
                        tv_date.setText(selectedDate + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    }, year, month, date);

            // Set the minimum date to today
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

            datePickerDialog.show();
        });

        terms.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://touristo-travel.netlify.app/terms");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });

        Intent intent = getIntent();
        Serializable serializableObject = intent.getSerializableExtra("pack");

        if (serializableObject instanceof popularmodel) {
            model = (popularmodel) serializableObject;

            btnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tv_date.getText().toString().isEmpty()) {
                        tv_date.setError("Please select the date");
                        return;
                    }
                    Intent intent = new Intent(PackDetail.this, BookActivity.class);
                    intent.putExtra("pack", model);
                    intent.putExtra("date", tv_date.getText().toString());
                    startActivity(intent);
                }
            });

            // Populate the UI with the model data
            tvtitle.setText(model.getTittle());
            tvlocation.setText(model.getLocation());
            tvprice.setText(model.getPrice() + "/-");
            tvbed.setText(model.getBed() + " Bed");
            tvday.setText(model.getDuration());
            tvguide.setText(model.getGuide());
            tvwifi.setText(model.getWifi());
            tvdesc.setText(model.getDescription());

            Glide.with(this)
                    .load(model.getPic())
                    .into(image);
        } else {
            // Handle the case where the object is not of the expected type
        }
    }
}
