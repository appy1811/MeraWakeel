package com.meravakeel.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }
    public void gotoplogin(View view) {
        Intent intent= new Intent(getApplicationContext(),ULoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void gotohlogin(View view) {
        Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }
}