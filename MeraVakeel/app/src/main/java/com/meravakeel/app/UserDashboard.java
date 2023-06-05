package com.meravakeel.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class UserDashboard extends AppCompatActivity {
    List<MyList> myLists;
    RecyclerView rv;
    MyUAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        getSupportActionBar().hide();
        rv = (RecyclerView) findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        myLists = new ArrayList<>();

        getdata();
    }

    private void getdata() {
        myLists.add(new MyList(R.drawable.lawyer, "Profile"));
        myLists.add(new MyList(R.drawable.user, "Lawyer Details"));
        myLists.add(new MyList(R.drawable.law, "Law Details"));
        myLists.add(new MyList(R.drawable.search, "Saved Search"));
        myLists.add(new MyList(R.drawable.law, "IPC"));
        myLists.add(new MyList(R.drawable.law, "CRPC"));

        myLists.add(new MyList(R.drawable.info, "Abt App"));
        myLists.add(new MyList(R.drawable.logout, "Logout"));
        adapter = new MyUAdapter(myLists, this);
        rv.setAdapter(adapter);

    }
}