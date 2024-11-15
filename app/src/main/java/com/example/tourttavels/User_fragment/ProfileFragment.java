package com.example.tourttavels.User_fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.tourttavels.Activities.Home;
import com.example.tourttavels.Activities.login;
import com.example.tourttavels.Constantdata;
import com.example.tourttavels.Model.VersionModel;
import com.example.tourttavels.R;
import com.example.tourttavels.pinfo;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class ProfileFragment extends Fragment {

    ImageView image1;
    File file;
    TextView camera, tvvers, Profile_name;
    LottieAnimationView lottie;
    VersionModel model;
    View view;
    Button btnlogout;
    CardView cv_personal, cv_aboutus, cv_help, cv_version;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        image1 = view.findViewById(R.id.image1);
        camera = view.findViewById(R.id.camera);
        btnlogout = view.findViewById(R.id.btnlogout);
        cv_personal = view.findViewById(R.id.cv_personal);
        cv_aboutus = view.findViewById(R.id.cv_aboutus);
        cv_help = view.findViewById(R.id.cv_help);
        tvvers = view.findViewById(R.id.tvvers);
        cv_version = view.findViewById(R.id.cv_version);
        Profile_name = view.findViewById(R.id.Profile_name);
        lottie = view.findViewById(R.id.lottie);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constantdata.SP_LOGIN, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Constantdata.SP_USERNAME, "");
        Profile_name.setText(username);

        btnlogout.setOnClickListener(v -> new AlertDialog.Builder(getContext())
                .setTitle("Confirm Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Intent i1 = new Intent(getActivity(), login.class);
                    startActivity(i1);
                    Toast.makeText(getContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor ed = sharedPreferences.edit();
                    ed.clear();
                    ed.apply();
                })
                .setNegativeButton("No", null)
                .show());

        lottie.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
            lottie.playAnimation();
            new Handler().postDelayed(() -> {
                Intent i1 = new Intent(getActivity(), Home.class);
                startActivity(i1);
            }, 4000);
        });

        SharedPreferences sp = getActivity().getSharedPreferences(Constantdata.SP_LOGIN, MODE_PRIVATE);
        String pic = sp.getString(Constantdata.KEY_PIC, "");
        loadImageFromPath(pic);

        camera.setOnClickListener(v -> ImagePicker.with(getActivity())
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start());

        cv_aboutus.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://touristo-travel.netlify.app/aboutus");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });

        cv_help.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dhruvilmavawala4@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Help Request");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Please provide your query or issue here...");

            Intent gmailIntent = new Intent(emailIntent);
            gmailIntent.setPackage("com.google.android.gm");

            Intent yahooIntent = new Intent(emailIntent);
            yahooIntent.setPackage("com.yahoo.mobile.client.android.mail");

            Intent chooserIntent = Intent.createChooser(emailIntent, "Send email using...");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{gmailIntent, yahooIntent});

            try {
                startActivity(chooserIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
            }
        });

        checkForVersionUpdat();

        cv_version.setOnClickListener(v -> checkForVersionUpdate());

        cv_personal.setOnClickListener(v -> startActivity(new Intent(getContext(), pinfo.class)));
    }

    private void checkForVersionUpdate() {
        DatabaseReference versionRef = FirebaseDatabase.getInstance().getReference("version");
        versionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        model = snapshot.getValue(VersionModel.class);
                        if (model != null) {
                            String latestVersion = model.getVers();
                            String currentVersion = tvvers.getText().toString();
                            if (currentVersion.equals(latestVersion)) {
                                Toast.makeText(getContext(), "App is up to Date", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Version Not Matched. Please update!", Toast.LENGTH_LONG).show();
                                Uri uri = android.net.Uri.parse("https://touristo-travel.netlify.app/download");
                                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                            }
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "No version data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to retrieve version", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkForVersionUpdat() {
        DatabaseReference versionRef = FirebaseDatabase.getInstance().getReference("version");
        versionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        model = snapshot.getValue(VersionModel.class);
                        if (model != null) {
                            String latestVersion = model.getVers();
                            String currentVersion = tvvers.getText().toString();
                            if (!currentVersion.equals(latestVersion)) {
                                tvvers.setTextColor(getResources().getColor(R.color.red));
                                showVersionUpdateNotification(latestVersion);
                            }
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "No version data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to retrieve version", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showVersionUpdateNotification(String latestVersion) {
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "version_update_channel";

        // Create Notification Channel for API 26+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Version Update Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), channelId)
                .setSmallIcon(R.mipmap.ic_launcher) // Use your app icon here
                .setContentTitle("Version Update Available")
                .setContentText("A new version " + latestVersion + " is available. Please update the app.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        // Show the notification
        notificationManager.notify(1, builder.build());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.getData() != null) {
            Uri uri = data.getData();
            image1.setImageURI(uri);

            SharedPreferences sp = getActivity().getSharedPreferences(Constantdata.SP_LOGIN, MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString(Constantdata.KEY_PIC, uri.getPath());
            ed.apply();
        }
    }

    private void loadImageFromPath(String filePath) {
        file = new File(filePath);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            image1.setImageBitmap(bitmap);
        } else {
            image1.setImageResource(R.mipmap.profile); // Set a default image
        }
    }
}
