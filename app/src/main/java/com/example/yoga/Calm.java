package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Calm extends AppCompatActivity {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calm);
        MediaController mediaController=new MediaController(this);
        videoView=findViewById(R.id.vid_calm);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/perfect-health-c41e6.appspot.com/o/Anxiety.mp4?alt=media&token=c91b030b-bb35-4692-8df7-1d48693aa069");
        videoView.setVideoURI(uri);
    }
}