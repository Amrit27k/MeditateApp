package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URI;

public class BeginnerMediation extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_mediation);

        MediaController mediaController=new MediaController(this);
        videoView=findViewById(R.id.video1);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        Uri uri= Uri.parse("https://firebasestorage.googleapis.com/v0/b/perfect-health-c41e6.appspot.com/o/beginnermediation.mp4?alt=media&token=db81fd10-66d1-4c45-9d52-df9ff94902f3");
        videoView.setVideoURI(uri);
        




    }
}