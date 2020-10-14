package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Mountain extends AppCompatActivity {
    TextView textView,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);

        textView=findViewById(R.id.mountain_text);
        title=findViewById(R.id.title1);
        Intent i = getIntent();
        String titletext = i.getStringExtra("title");
        title.setText(titletext);
        textView.setText("Focus :\tLegs,\tPosture\n" +
                "Level:\tBeginning Total\t\n" +
                "Time :\t60\tseconds\n" +
                "Indications :\tSciatica,\tstress\n" +
                "Contraindications :\tLow\tblood\tpressure,\tdizziness,\tlight-headedness,\theadache\n");
        String para=textView.getText().toString();

        //set back button

    }
}