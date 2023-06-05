package com.meravakeel.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.meravakeel.app.pojo.MyGlobalVar;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile extends AppCompatActivity {
EditText name,pass,mobno,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.name);
        pass = findViewById(R.id.upass);
        mobno = findViewById(R.id.phno);
        email = findViewById(R.id.emailid);

        //get all dtls

        name.setText(""+ MyGlobalVar.u.getFullname());
        mobno.setText(""+ MyGlobalVar.u.getPhno().substring(3));
        pass.setText(""+ MyGlobalVar.u.getPassword());
        email.setText(""+ MyGlobalVar.u.getEmailid());


    }


    public void RegP(View v)
    {
        Map<String,Object> map=new HashMap<>();
        //dname, address, mobno, emailid, specialization, totalexp, qualification, Bid, uname, password;
        map.put("name",name.getText().toString());
        map.put("password",pass.getText().toString());
        map.put("phno","+91"+mobno.getText().toString());
        map.put("email",email.getText().toString());
if(name.getText().toString().equals("") || email.getText().toString().equals("") || mobno.getText().toString().equals("") || pass.getText().toString().equals(""))
{
    Toast.makeText(getApplicationContext(),"Please Fill Values",Toast.LENGTH_LONG).show();
    return;
}
        if (!validatePassword())
        {
            return;
        }
        if (!validatePhno())
        {
            return;
        }
MyGlobalVar.u.setPhno("+91"+mobno.getText().toString());
        MyGlobalVar.u.setFullname(""+name.getText().toString());
        MyGlobalVar.u.setPassword(""+pass.getText().toString());

//save everythimg
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference reference = firebaseDatabase.getReference();
        Query query = reference.child("tbluser").orderByChild("email").equalTo(MyGlobalVar.un);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot nodeDataSnapshot = dataSnapshot.getChildren().iterator().next();
                String key = nodeDataSnapshot.getKey(); // this key is `K1NRz9l5PU_0CFDtgXz`
                String path = "/" + dataSnapshot.getKey() + "/" + key;


                reference.child(path).updateChildren(map);
                MyGlobalVar.cp="p";
                Toast.makeText(getApplicationContext(),"Profile changed successfully",Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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