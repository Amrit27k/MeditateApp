package com.example.yoga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextView newuser;
    private Button btnlogin;
    private EditText edt_log_mob,edt_log_pass;
    private ImageView img_login;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        newuser=findViewById(R.id.txtNewuser);
        btnlogin=findViewById(R.id.btnlogin);
        img_login=findViewById(R.id.imagelogin);
        edt_log_mob=findViewById(R.id.edtmobile);
        edt_log_pass=findViewById(R.id.edtpassword);
        mAuth = FirebaseAuth.getInstance();

        // checksession();

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String eemail=edt_log_mob.getText().toString().trim();
                final String epass=edt_log_pass.getText().toString().trim();

                if(TextUtils.isEmpty(epass))
                {
                    edt_log_pass.setError("Password is Required");
                    return;
                }
                if(TextUtils.isEmpty(eemail))
                {
                    edt_log_mob.setError("E-mail ID is required");
                    return;
                }


                mAuth.signInWithEmailAndPassword(eemail,epass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            new CurrentUser(Login.this).setPass(epass);
                            Toast.makeText(Login.this, "Login Succcessful", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(Login.this,Tabs.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }



}