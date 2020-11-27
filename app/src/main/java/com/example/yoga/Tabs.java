package com.example.yoga;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.DateFormat;
import java.util.Calendar;

public class Tabs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ViewPager pager;
    TabLayout mTabLayout;
    TabItem firstItem, secondItem, thirdItem;
    PagerAdapter pagerAdapter;
    RelativeLayout beginner;
    LinearLayout beginneryoga;
    DocumentReference documentReference1;
    FirebaseAuth mAuth;
    String userId;
    FirebaseUser user;
    FirebaseFirestore fstore;
    TextView user_name;



    @SuppressLint({"ResourceType", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        pager = findViewById(R.id.view_page);
        mTabLayout = findViewById(R.id.tab_layout);
        beginneryoga = findViewById(R.id.beginnerYoga12);
        firstItem = findViewById(R.id.first_yoga);
        secondItem = findViewById(R.id.second_mediate);
        thirdItem = findViewById(R.id.third_course);
        beginner = findViewById(R.id.beginnerYoga);
       user_name=findViewById(R.id.user_name);
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        user = mAuth.getCurrentUser();
        fstore = FirebaseFirestore.getInstance();



        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mTabLayout.getTabCount());
        pager.setAdapter(pagerAdapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }


        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            drawerLayout.closeDrawer(GravityCompat.START);




            if (item.getItemId() == R.id.nav_logout) {
                FirebaseAuth.getInstance().signOut();
                new CurrentUser(Tabs.this).removeUser();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();

            }
            if (item.getItemId() == R.id.nav_calendra) {
                DialogFragment datePicker = new DatePickerFragmnet();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
            if (item.getItemId() == R.id.nav_music) {
                startActivity(new Intent(getApplicationContext(), Music.class));
            }
            if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));

            }
            if (item.getItemId() == R.id.nav_about) {
                startActivity(new Intent(getApplicationContext(), AboutUs.class));
            }

            if (item.getItemId() == R.id.nav_alarm) {
                startActivity(new Intent(getApplicationContext(), Alarm_man.class));
            }
            if (item.getItemId() == R.id.nav_invite) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Perfect Health");
                    String sharemsg = "Perfect Health\nhttps://play.google.com/store/apps/details?=en" + BuildConfig.APPLICATION_ID + "\n\n";
                    intent.putExtra(Intent.EXTRA_TEXT, sharemsg);
                    startActivity(Intent.createChooser(intent, "Share by"));
                } catch (Exception e) {
                    Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        }


        FragmentYoga fragment = new FragmentYoga();
        FragmentManager manager = getSupportFragmentManager();

        public FragmentManager getManager () {
            return manager;
        }

        public FragmentYoga getFragment () {
            return fragment;
        }

        @Override
        public void onDateSet (DatePicker datePicker,int year, int month, int dayOfMonth){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());


        }
    }
