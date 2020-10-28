package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class Beginner extends AppCompatActivity {

    RecyclerView recyclerView;
    BeginnerAdapter adapter;
    List<BeginnerYoga> beginnerYogaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);


        recyclerView=findViewById(R.id.recycle1);
        beginnerYogaList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        beginnerYogaList.add(
                new BeginnerYoga(
                        "The Mountain\n(Tadasana)",
                        "Easy",
                        "30s",
                        "40\nkcal",
                        R.drawable.mountain
                ));
        beginnerYogaList.add(
                new BeginnerYoga(
                        "Standing Forword Fold\n(Uthanasana)",
                        "Easy",
                        "30s",
                        "30\nkcal",
                        R.drawable.beginner
                ));

        adapter=new BeginnerAdapter(this,beginnerYogaList);
        recyclerView.setAdapter(adapter);


    }
}