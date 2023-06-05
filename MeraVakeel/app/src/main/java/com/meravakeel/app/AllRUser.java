package com.meravakeel.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meravakeel.app.adapter.UserAdapter;
import com.meravakeel.app.pojo.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AllRUser extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter adapter;
    List<User> drList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ruser);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        drList = new ArrayList<>();

        loadHeroes();
        adapter = new UserAdapter(drList, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    private void loadHeroes() {

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("tbluser");
        //   Query query =  databaseReference.orderByChild("uname").equalTo(email);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int i=1;
                System.out.println("hi");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    System.out.println(dataSnapshot1.child("fname").getValue(String.class));

                    drList.add(new User(""+i, dataSnapshot1.child("password").getValue(String.class),
                            dataSnapshot1.child("email").getValue(String.class),
                            dataSnapshot1.child("name").getValue(String.class)+"",""+dataSnapshot1.child("phno").getValue(String.class)) );


                    i++;
                }
                adapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }

        });

        //    heroList.add(new Doctor("1", "Dr. Raj Joshi", "Pune", "9874563210","raj.joshi@gmail.com", "Neuro Surgon", "15 Years", "a","1", "","") );
        //  heroList.add(new Doctor("1", "Dr. Rama Naik", "Pune", "9874563210","raj.joshi@gmail.com", "Neuro Surgon", "15 Years", "a","1", "","") );



    }
}