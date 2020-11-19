package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Depression extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression);

        MediaController mediaController=new MediaController(this);
        videoView=findViewById(R.id.vid_depr);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/perfect-health-c41e6.appspot.com/o/videoplayback.mp4?alt=media&token=fe7f8f1f-af9f-423e-8f69-20aa9bdaa620");
        videoView.setVideoURI(uri);

    }
}