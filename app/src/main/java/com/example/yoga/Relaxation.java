package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Relaxation extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxation);

        MediaController mediaController=new MediaController(this);
        videoView=findViewById(R.id.vid_rel);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/perfect-health-c41e6.appspot.com/o/Relaxation.mp4?alt=media&token=ef86272e-e1b7-4903-a52c-71929772fd4b");
        videoView.setVideoURI(uri);
    }
}