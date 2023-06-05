package com.meravakeel.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.meravakeel.app.adapter.SearchAdapter;
import com.meravakeel.app.pojo.MyGlobalVar;
import com.meravakeel.app.pojo.MySearch;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AllUSearch extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchAdapter adapter;
    List<MySearch> searchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_usearch);
        recyclerView = findViewById(R.id.recyclerSView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchList = new ArrayList<>();

        loadHeroes();
        adapter = new SearchAdapter(searchList, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    private void loadHeroes() {

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("tblsearchdata");
           Query query =  databaseReference.orderByChild("uname").equalTo(MyGlobalVar.un);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int i=1;
                System.out.println("hi");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    System.out.println(dataSnapshot1.child("uname").getValue(String.class));

                    searchList.add(new MySearch(""+i, dataSnapshot1.child("uname").getValue(String.class),
                            dataSnapshot1.child("date").getValue(String.class),
                            dataSnapshot1.child("searchdata").getValue(String.class)+"",
                            dataSnapshot1.child("fname").getValue(String.class)) );


                    i++;
                }
                adapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }

        });




    }
}