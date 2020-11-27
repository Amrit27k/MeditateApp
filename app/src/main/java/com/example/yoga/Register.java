package com.example.yoga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "Register";
    private TextView appname,alreadyregister;
    private EditText edtname,edtemail,edtmob,edtpassword;
    private Button btnregister;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;
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
        fStore = FirebaseFirestore.getInstance();
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
                else if(!Patterns.EMAIL_ADDRESS.matcher(eemail).matches())
                {
                    edtemail.setError("Please Enter a Valid Email address");
                    return ;
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
                            userID =mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference= fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fname",ename);
                            user.put("email",eemail);
                            user.put("mobNo",emob);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                }
                            });
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


