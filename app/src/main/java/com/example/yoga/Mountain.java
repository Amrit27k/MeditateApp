package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Mountain extends AppCompatActivity {
    TextView textView,title;
    ImageView image1,youlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);

        textView=findViewById(R.id.mountain_text);
        image1=findViewById(R.id.mountain_image);
        youlink=findViewById(R.id.youtubelink);
        title=findViewById(R.id.title1);
        Intent i = getIntent();
        String titletext = i.getStringExtra("title");

        title.setText(titletext);
        if(titletext.equals("The Mountain\n(Tadasana)")) {
            textView.setText("Focus :\tLegs,\tPosture\n" +
                    "Level:\tBeginning Total\t\n" +
                    "Time :\t60\tseconds\n" +
                    "Indications :\tSciatica,\tstress\n" +
                    "Contraindications :\tLow\tblood\tpressure,\tdizziness,\tlight-headedness,\theadache\n");

            String para = textView.getText().toString();
        }
        else if(titletext.equals("Standing Forword Fold\n(Uthanasana)"))
        {
            textView.setText("Focus:\tHamstrings,\tcalves,\tback\n " +
                    "Level:\tBeginning Total\n" +
                    "Time:\t60\tseconds\n" +
                    "Indications:\tStress\n" +
                    "Contraindications:\tBack\tinjury\n"+
                    "For More Information"
                    
                );
            youlink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoUrl("https://youtu.be/g7Uhp5tphAs");
                }


            });
            image1.setImageResource(R.drawable.standingforwardfold);
           String para = textView.getText().toString();
        }
        else
        {

        }


        //set back button

    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
   }
}