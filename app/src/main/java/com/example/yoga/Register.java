package com.example.yoga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private TextView appname,alreadyregister;
    private EditText edtname,edtemail,edtmob,edtpassword;
    private Button btnregister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        appname=findViewById(R.id.appname);


        edtname=findViewById(R.id.edttxtname);
        edtpassword=findViewById(R.id.edttxtpassword);
        edtemail=findViewById(R.id.edttxtemail);
        edtmob=findViewById(R.id.edttxtmobile);
        btnregister=findViewById(R.id.btnregister1);
        alreadyregister=findViewById(R.id.alreadyregister);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),Tabs.class));
            finish();
        }


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ename=edtname.getText().toString();
                final String eemail=edtemail.getText().toString().trim();
                final String epass=edtpassword.getText().toString().trim();
                final String emob=edtmob.getText().toString().trim();
                if(TextUtils.isEmpty(ename))
                {
                    edtname.setError("This Field is required ");
                    return;

                }
                if(TextUtils.isEmpty(epass))
                {
                    edtpassword.setError("This Field is required");
                    return;
                }
                if(TextUtils.isEmpty(eemail))
                {
                    edtemail.setError("This Field  is required");
                    return;
                }
                if(TextUtils.isEmpty(emob))
                {
                    edtmob.setError("This Field is required");
                    return;
                }
                if(epass.length()<6)
                {
                    edtpassword.setError("Password should be of atleast 6 character");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(eemail,epass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this, "Registation Succcessful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,Login.class));
                        }
                        else
                        {
                            Toast.makeText(Register.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        });
        alreadyregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Register.this,Login.class);
                startActivity(intent1);
            }
        });

    }


}