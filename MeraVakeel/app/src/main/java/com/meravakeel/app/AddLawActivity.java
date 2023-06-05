package com.meravakeel.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddLawActivity extends AppCompatActivity {
    Button submit,back;
    EditText mainT,subT,desc,ref,wcbd;

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_law);
        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AllLawDtls.class));
                finish();
            }
        });
        mainT = findViewById(R.id.mainT);
        subT = findViewById(R.id.subT);
        desc = findViewById(R.id.desc);
        wcbd = findViewById(R.id.wcbd);
        ref = findViewById(R.id.refd);



        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });

    }
    String bname;

    private void processinsert()
    {
        Map<String,Object> map=new HashMap<>();
        //dname, address, mobno, emailid, specialization, totalexp, qualification, Bid, uname, password;
        map.put("mainT",mainT.getText().toString());

        map.put("subT",subT.getText().toString());
        map.put("desc",desc.getText().toString());
        map.put("wcbd",wcbd.getText().toString());
        map.put("lawRef",ref.getText().toString());
        if(subT.getText().toString().equals("") || desc.getText().toString().equals("") || wcbd.getText().toString().equals("") || ref.getText().toString().equals(""))
        {
            Toast.makeText(desc.getContext(), "Please fill values" ,Toast.LENGTH_LONG);
            return;
        }
        FirebaseDatabase.getInstance().getReference().child("tbllawdtls").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        startActivity(new Intent(getApplicationContext(), AllLawDtls.class));
                        finish();
                        Toast.makeText(getApplicationContext(),"Details Created Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }



}