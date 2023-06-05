package com.meravakeel.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.meravakeel.app.adapter.IPCLawUAdapter;
import com.meravakeel.app.pojo.IPC;

public class AllUCrpc extends AppCompatActivity {
    RecyclerView recview;
    IPCLawUAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ucrpc);
        recview=(RecyclerView)findViewById(R.id.recviewcrpc);
        recview.setLayoutManager(new LinearLayoutManager(this));
        recview.setItemAnimator(null);
        FirebaseRecyclerOptions<IPC> options =
                new FirebaseRecyclerOptions.Builder<IPC>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("tblcrpc"), IPC.class)
                        .build();

        adapter=new IPCLawUAdapter(options);

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
}