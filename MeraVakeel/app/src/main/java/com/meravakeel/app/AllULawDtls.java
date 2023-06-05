package com.meravakeel.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.meravakeel.app.adapter.LawAdapter;
import com.meravakeel.app.adapter.LawUAdapter;
import com.meravakeel.app.adapter.LawUAdapter1;
import com.meravakeel.app.pojo.Law;

public class AllULawDtls extends AppCompatActivity {
    RecyclerView recview;
    LawUAdapter1 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ulaw_dtls);
        setTitle("Search here..");

        recview=(RecyclerView)findViewById(R.id.recviewlaw);
        recview.setLayoutManager(new LinearLayoutManager(this));
        recview.setItemAnimator(null);
        FirebaseRecyclerOptions<Law> options =
                new FirebaseRecyclerOptions.Builder<Law>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("tbllawdtls"), Law.class)
                        .build();

        adapter=new LawUAdapter1(options);

        recview.setAdapter(adapter);


    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<Law> options =
                new FirebaseRecyclerOptions.Builder<Law>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("tbllawdtls").orderByChild("mainT").startAt(s).endAt(s+"\uf8ff"), Law.class)
                        .build();

        adapter=new LawUAdapter1(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }

}