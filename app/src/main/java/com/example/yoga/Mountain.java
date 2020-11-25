package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class Mountain extends AppCompatActivity {
    TextView textView,title;
    ImageView image1,youlink;

    private static long START_TIME_IN_MILLIS = 45000;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        textView=findViewById(R.id.mountain_text);
        image1=findViewById(R.id.mountain_image);
        youlink=findViewById(R.id.youtubelink);
        title=findViewById(R.id.title1);
        Intent i = getIntent();
        String titletext = i.getStringExtra("title");

        title.setText(titletext);
        if(titletext.equals("The Mountain\n(Tadasana)")) {

            textView.setText(Html.fromHtml("<b> Focus </b>: \tLegs,\tPosture<br>" +
                    "<b>Level</b> : \tBeginning Total\t<br>" +
                    "<b>Time</b> : \t60\tseconds<br>" +
                    "<b>Indications</b> : \tSciatica,\tstress<br>" +
                    "<b>Contraindications</b> : \tLow\tblood\tpressure,\tdizziness,\tlight-headedness,\theadache<br>"+
                    "<b>For More Information"));

            String para = textView.getText().toString();
            youlink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoUrl("https://youtu.be/2HTvZp5rPrg");
                }


            });

            mButtonStartPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTimerRunning) {
                        pauseTimer();
                    } else {
                        startTimer();
                    }
                }
            });

            mButtonReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTimer();
                }
            });
            updateCountDownText();
        }

        else if(titletext.equals("Standing Forword Fold\n(Uthanasana)"))
        {
            textView.setText(Html.fromHtml("<b>Focus</b> : \tHamstrings,\tcalves,\tback<br> " +
                    "<b>Level</b> : \tBeginning Total<br>" +
                    "<b>Time</b> : \t60\tseconds<br>" +
                    "<b>Indications</b> : \tStress<br>" +
                    "<b>Contraindications</b> : \tBack\tinjury<br>"+
                    "<b>For More Information</b>"
                    
                ));
            youlink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoUrl("https://youtu.be/g7Uhp5tphAs");
                }


            });
            image1.setImageResource(R.drawable.standingforward);
           String para = textView.getText().toString();
            mButtonStartPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTimerRunning) {
                        pauseTimer();
                    } else {
                        startTimer();
                    }
                }
            });

            mButtonReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTimer();
                }
            });
            updateCountDownText();

        }
        else
        {

        }


        //set back button

    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
   }
}