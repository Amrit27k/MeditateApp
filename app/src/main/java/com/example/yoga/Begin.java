package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Begin extends AppCompatActivity {

    private Button btnlogin1,btnregister1;
    private TextView txtname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin);
        txtname=findViewById(R.id.textview);
        btnlogin1=findViewById(R.id.main_login);
        btnregister1=findViewById(R.id.main_register);

        checksession();

        btnlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Begin.this,Login.class);
                startActivity(intent);
            }
        });
        btnregister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Begin.this, Register.class);
                startActivity(intent1);
            }
        });
    }

    private void checksession() {
        if (new CurrentUser(Begin.this).getPass() != "") {
            startActivity(new Intent(Begin.this, Tabs.class));
            finish();
        } else {
        }
    }

}