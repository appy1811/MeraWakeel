package com.meravakeel.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.meravakeel.app.pojo.Law;

public class ShowLawDtls extends AppCompatActivity {
    TextView mainT,subT,wcbd,ref,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_law_dtls);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mainT = findViewById(R.id.mainT);
        subT =  findViewById(R.id.subT);
        wcbd =  findViewById(R.id.whatcandone);
        ref =  findViewById(R.id.lawref);
        desc =  findViewById(R.id.desc);

        Law model= (Law) getIntent().getSerializableExtra("law");
        mainT.setText(""+model.getMainT());
        subT.setText(""+model.getSubT());
        desc.setText(""+model.getDesc());
        wcbd.setText(""+model.getWcbd());
        ref.setText(""+model.getLawref());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


                finish();


        return super.onOptionsItemSelected(item);
    }
    public void showLawD(View v)
    {
startActivity(new Intent(getApplicationContext(),AllULDtls.class));
    }
}