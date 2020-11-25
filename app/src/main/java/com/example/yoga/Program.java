package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Program extends AppCompatActivity {
    ImageView ulink1,ulink2,ulink3,ulink4,ulink5,ulink6,ulink7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        ulink1 = findViewById(R.id.y1);
        ulink2 = findViewById(R.id.y2);
        ulink3 = findViewById(R.id.y3);
        ulink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=cVRnZa3_ing");
            }
        });
        ulink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=hwKAYjo8Mlw");
            }
        });
        ulink3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=9kOCY0KNByw");
            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


}