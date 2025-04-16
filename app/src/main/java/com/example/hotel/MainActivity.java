package com.example.hotel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button ReservationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.clientHomeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        VideoView videoView = findViewById(R.id.videoBackground);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1); // met la vidéo dans res/raw/hotel_bg.mp4
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));

        ReservationBtn = findViewById(R.id.makeReservationButton);

        ReservationBtn.setOnClickListener(v -> {
            Intent RooomIntent = new Intent(MainActivity.this, SelectRoomActivity.class);
            startActivity(RooomIntent);
        });

    }
}