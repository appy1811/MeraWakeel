package com.meravakeel.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
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
import java.util.List;
import java.util.stream.Collectors;

public class ULoginActivity extends AppCompatActivity {

    private EditText editTextName, editTextPassword;
    String password = "";

    private FirebaseAuth mAuth;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulogin);
//        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        editTextName = findViewById(R.id.signin_id);

        editTextPassword = findViewById(R.id.signin_password);

    }
    public void openReg(View view) {
        Intent intent= new Intent(getApplicationContext(),Reg.class);
        startActivity(intent);
        finish();

    }
    public void gotoUSA(View view) {
        String email;
        email = editTextName.getText().toString();
        password = editTextPassword.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(ULoginActivity.this, "Please fill Credentials", Toast.LENGTH_LONG).show();
            return;
        }
        MyGlobalVar.tableName = "tbluser";


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(MyGlobalVar.tableName);
        Query query = databaseReference.orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<User> movies = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String name = dataSnapshot1.child("email").getValue(String.class);
                    String password = dataSnapshot1.child("password").getValue(String.class);
                    MyGlobalVar.un = name;
                    User u = new User();

                    System.out.println(name + "--" + password);
                    u.setUname(name);
                    u.setPassword(password);
                    u.setEmailid(name);
                    u.setFullname(""+dataSnapshot1.child("name").getValue(String.class));
                    u.setPhno(""+dataSnapshot1.child("phno").getValue(String.class));
                    MyGlobalVar.u=u;
                    movies.add(u);

                }

                filterResults(movies, password);


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }

        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterResults(final List<User> list, final String genre) {
        List<User> movies = new ArrayList<>();

        System.out.println(genre);

        movies = list.stream().filter(o -> o.getPassword().equals(genre)).collect(Collectors.toList());

        System.out.println(movies);
        if (movies.isEmpty()) {

                Toast.makeText(ULoginActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(ULoginActivity.this, "Welcome to MeraVakeel", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), UserDashboard.class));
            finish();


        }
    }
}

