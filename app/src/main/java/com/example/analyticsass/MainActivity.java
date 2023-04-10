package com.example.analyticsass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAnalytics mFirebaseAnalytics;

    RecyclerView recyclerView2;
    RecyclerView recyclerView;
    RecyclerView recyclerView3;
    long time1 ;
    long time2;
    long timeResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView3.setOnClickListener(this);
        recyclerView2.setOnClickListener(this);
        recyclerView.setOnClickListener(this);
        time1= System.currentTimeMillis();




    }

    public  void trackScreen(String screenname){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME,screenname );
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recyclerView3:
                Intent i = new Intent(getApplicationContext(), publicnotes.class);
                startActivity(i);
                break;
            case R.id.recyclerView2:
                Intent i2 = new Intent(getApplicationContext(), privatenotes.class);
                startActivity(i2);
                break;
            case R.id.recyclerView:
                Intent i3 = new Intent(getApplicationContext(), notes.class);
                startActivity(i3);
                break;

        }
        @Override
        protected void onResume () {
            super.onResume();
            trackScreen("main screen");
        }

        @Override
        protected void onDestroy () {
            time2 = System.currentTimeMillis();
            timeEvent(time1, time2);
            super.onDestroy();

        }
        public void timeEvent ( long t1, long t2){
            timeResult = (t2 - t1);
            Bundle params = new Bundle();
            params.putString("userid", UUID.randomUUID().toString());
            params.putLong("time_spend", timeResult);
            mFirebaseAnalytics.logEvent("timeMainScreen", params);

        }
    }
    }