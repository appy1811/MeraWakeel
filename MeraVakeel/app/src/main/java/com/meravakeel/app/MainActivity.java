package com.meravakeel.app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.meravakeel.app.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
        Handler h=new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                //open new activity
                //we need object of intent class
                //to- from
                Intent intent= new Intent(getApplicationContext(),SelectionActivity.class);
                startActivity(intent);
                finish();
                //   finish();
            }
        }, 5000);
    }
}