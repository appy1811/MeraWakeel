package com.meravakeel.app.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.meravakeel.app.R;
import com.meravakeel.app.ShowLawDtls;
import com.meravakeel.app.pojo.IPC;
import com.meravakeel.app.pojo.Law;

public class IPCLawUAdapter extends FirebaseRecyclerAdapter<IPC, IPCLawUAdapter.myviewholder>
{
    public IPCLawUAdapter(@NonNull FirebaseRecyclerOptions<IPC> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final IPC model)
    {


        holder.mainT.setText(""+model.getSection());
        holder.subT.setText(""+model.getDtls());





    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowlawuname,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder


    {

        ImageView edit,delete;
        TextView mainT,subT,wcbd,ref,desc;
LinearLayoutCompat lv;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
lv=itemView.findViewById(R.id.showlaw);
            mainT = itemView.findViewById(R.id.mainT);
            subT = itemView.findViewById(R.id.subT);



        }
    }
}
