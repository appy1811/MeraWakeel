package com.meravakeel.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.meravakeel.app.pojo.MyGlobalVar;
import com.meravakeel.app.pojo.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg extends AppCompatActivity {
EditText name,pass,mobno,email;
boolean isExist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        name = findViewById(R.id.name);
        pass = findViewById(R.id.upass);
        mobno = findViewById(R.id.phno);
        email = findViewById(R.id.emailid);
        isExist=false;
    }
    public boolean isExisted()
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tbluser");
        Query query = databaseReference.orderByChild("email").equalTo(email.getText().toString());
        query.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<User> movies = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Toast.makeText(getApplicationContext(), "User-Email Id ALready Exist", Toast.LENGTH_LONG).show();
                    isExist = true;
                    return ;

                }


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }

        });
        databaseReference = FirebaseDatabase.getInstance().getReference("tbluser");
        query = databaseReference.orderByChild("phno").equalTo(email.getText().toString());
        query.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<User> movies = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    isExist = true;
                    Toast.makeText(getApplicationContext(), "User=Mobile No ALready Exist", Toast.LENGTH_LONG).show();

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
    public void Reg(View v) {
        Map<String, Object> map = new HashMap<>();
        //dname, address, mobno, emailid, specialization, totalexp, qualification, Bid, uname, password;
        map.put("name", name.getText().toString());
        map.put("password", pass.getText().toString());
        map.put("phno", "+91" + mobno.getText().toString());
        map.put("email", email.getText().toString());
        if (name.getText().toString().equals("") || email.getText().toString().equals("") || mobno.getText().toString().equals("") || pass.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please Fill Values", Toast.LENGTH_LONG).show();
            return;
        }
        if (!validatePassword()) {
            return;
        }
        if (!validatePhno()) {
            return;
        }
        String emailToText=email.getText().toString();
        if (!Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(getApplicationContext(), "Enter valid Email address !", Toast.LENGTH_SHORT).show();
            return;
        }
//chk the user exist or not
        if (!isExisted()) {
            return;
        }

        FirebaseDatabase.getInstance().getReference().child("tbluser").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        startActivity(new Intent(getApplicationContext(), AllLDtls.class));
                        finish();
                        Toast.makeText(getApplicationContext(),"Registration done Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not register",Toast.LENGTH_LONG).show();
                    }
                });


    }
    private boolean validatePassword() {
        String passwordInput =  pass.getText().toString().trim();

        if (!passwordInput.matches(".*[0-9].*")) {
            Toast.makeText(getApplicationContext(), "Password should contain at least 1 digit", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!passwordInput.matches(".*[a-z].*")) {
            Toast.makeText(getApplicationContext(), "Password should contain at least 1 lower case letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!passwordInput.matches(".*[A-Z].*")) {
            Toast.makeText(getApplicationContext(), "Password should contain at least 1 upper case letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!passwordInput.matches(".*[a-zA-Z].*")) {
            Toast.makeText(getApplicationContext(), "Password should contain a letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!passwordInput.matches( ".{8,}")) {
            Toast.makeText(getApplicationContext(), "Password should contain 8 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
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