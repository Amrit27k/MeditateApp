package com.example.yoga;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    private CircleImageView profileImageView;
    private TextView displayName, displaymail, display_mobno,fnamesideimg;
    private DocumentReference documentReference;
    private FirebaseAuth mAuth;
    private Uri imageUri;
    String userId;
    private StorageTask uploadTask;
    private StorageReference storageProfileRef;
    FirebaseUser user;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileImageView = findViewById(R.id.profile_image);
        displayName = findViewById(R.id.full_name_profile);
        displaymail = findViewById(R.id.email_profile);
        display_mobno = findViewById(R.id.phoneNo_profile);
        fnamesideimg = findViewById(R.id.fullname);
        mAuth = FirebaseAuth.getInstance();
        userId=mAuth.getCurrentUser().getUid();
        user = mAuth.getCurrentUser();
        fstore = FirebaseFirestore.getInstance();
        storageProfileRef = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageProfileRef.child("images"+ userId + ".jpg");

        DocumentReference documentReference=fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                displayName.setText(value.getString("fname"));
                displaymail.setText(value.getString("email"));
                display_mobno.setText(value.getString("mobNo"));
                fnamesideimg.setText(value.getString("fname"));
            }
        });

        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerInside().into(profileImageView);
            }
        });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //profileImageView.setImageURI(imageUri);
                uploadImage(imageUri);
            }
        }
    }

    private void uploadImage(Uri imageUri) {


        final StorageReference fileRef = storageProfileRef.child("images").child(userId + "profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerInside().into(profileImageView);
                    }
                });
            }
        })
         .addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(Profile.this,"Upload Failed",Toast.LENGTH_SHORT).show();
             }
         });
    }
}