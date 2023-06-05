package com.meravakeel.app.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.meravakeel.app.R;
import com.meravakeel.app.pojo.Law;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;
import java.util.Map;

public class LawUAdapter extends FirebaseRecyclerAdapter<Law, LawUAdapter.myviewholder>
{
    public LawUAdapter(@NonNull FirebaseRecyclerOptions<Law> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Law model)
    {

        holder.mainT.setText(""+model.getMainT());
        holder.subT.setText(""+model.getSubT());
        holder.desc.setText(""+model.getDesc());
        holder.wcbd.setText(""+model.getWcbd());
        holder.ref.setText(""+model.getLawref());







    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowlawu,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder


    {

        ImageView edit,delete;
        TextView mainT,subT,wcbd,ref,desc;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            mainT = itemView.findViewById(R.id.mainT);
            subT = itemView.findViewById(R.id.subT);
            wcbd = itemView.findViewById(R.id.whatcandone);
            ref = itemView.findViewById(R.id.lawref);
            desc = itemView.findViewById(R.id.desc);



        }
    }
}
