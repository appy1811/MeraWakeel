package com.meravakeel.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.meravakeel.app.pojo.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddLawyerActivity extends AppCompatActivity {
    Button submit,back;
    EditText dname, add, mobno, email,spc,
            totexp, qual;
boolean isExist=false;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lawyer);
        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AllLDtls.class));
                finish();
            }
        });
        dname = findViewById(R.id.dname);
        add = findViewById(R.id.dadd);
        mobno = findViewById(R.id.dmobno);
        email = findViewById(R.id.demailid);
        spc = findViewById(R.id.specialization);
        qual = findViewById(R.id.qualification);
        totexp = findViewById(R.id.totalexp);


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
         map.put("address",add.getText().toString());

        map.put("dname",dname.getText().toString());
        map.put("mobno",mobno.getText().toString());
        map.put("emailid",email.getText().toString());
        map.put("specialization",spc.getText().toString());
        map.put("qualification",qual.getText().toString());

        map.put("totalexp",totexp.getText().toString());
        if(add.getText().toString().equals("") || dname.getText().toString().equals("") || mobno.getText().toString().equals("") || email.getText().toString().equals("") || spc.getText().toString().equals("") || qual.getText().toString().equals("")  )
        {
            Toast.makeText(getApplicationContext(), "Please fill values" ,Toast.LENGTH_LONG);
            return;
        }


        if (!validatePhno())
        {
            return;
        }
        String emailToText=email.getText().toString();
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {

        } else {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isExisted()) {
            return;
        }

        FirebaseDatabase.getInstance().getReference().child("tbllawyerdtls").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        startActivity(new Intent(getApplicationContext(), AllLDtls.class));
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
    public boolean isExisted()
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tbllawyerdtls");
        Query query = databaseReference.orderByChild("emailid").equalTo(email.getText().toString());
        query.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<User> movies = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Toast.makeText(getApplicationContext(), "Lawyer-Email Id ALready Exist", Toast.LENGTH_LONG).show();
                    isExist = true;
                    return ;

                }


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }

        });
        databaseReference = FirebaseDatabase.getInstance().getReference("tbllawyerdtls");
        query = databaseReference.orderByChild("mobno").equalTo(email.getText().toString());
        query.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<User> movies = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    isExist = true;
                    Toast.makeText(getApplicationContext(), "Mobile No Already Exist", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        if(isExist)
            return true;
        return false;
    }
    private boolean validatePhno() {
        String passwordInput =  mobno.getText().toString().trim();
        Pattern pattern = Pattern.compile("\\d{10}");

        Matcher matcher = pattern.matcher(passwordInput);
        if(!matcher.matches())
        {
            Toast.makeText(getApplicationContext(), "Mobile No should contain 10 digits", Toast.LENGTH_SHORT).show();
            return false;

        }
        else {
            return true;
        }
    }
}