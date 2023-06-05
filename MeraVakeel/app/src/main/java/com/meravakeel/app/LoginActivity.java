package com.meravakeel.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.meravakeel.app.databinding.ActivityLoginBinding;
import com.meravakeel.app.pojo.MyGlobalVar;
import com.meravakeel.app.pojo.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextName,  editTextPassword;
String password="";

    private FirebaseAuth mAuth;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        editTextName = findViewById(R.id.signin_id);

        editTextPassword = findViewById(R.id.signin_password);

    }
    public void gotoSA(View view) {
        String email;
        email=editTextName.getText().toString();
        password=editTextPassword.getText().toString();

if(email.equals("") || password.equals(""))
{
    Toast.makeText(LoginActivity.this, "Please fill Credentials", Toast.LENGTH_LONG).show();
return;
}
            MyGlobalVar.tableName="tbladmin";


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("tbladmin");
        Query query =  databaseReference.orderByChild("uname").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<User> movies = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String name = dataSnapshot1.child("uname").getValue(String.class);
                    String password = dataSnapshot1.child("password").getValue(String.class);
                    MyGlobalVar.un=name;
                    User u = new User();
                    System.out.println(email);
System.out.println(name+"--"+password);
                    u.setUname(name);
                    u.setPassword(password);

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
        if(movies.isEmpty())
        {
            if(MyGlobalVar.cp.equals(""))
                Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();

        }
        else
        {

                Toast.makeText(LoginActivity.this, "Welcome to MeraVakeel", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
                finish();


        }
    }

}