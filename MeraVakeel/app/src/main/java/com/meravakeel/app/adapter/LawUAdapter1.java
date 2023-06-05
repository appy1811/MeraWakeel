package com.meravakeel.app.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.meravakeel.app.R;
import com.meravakeel.app.ShowLawDtls;
import com.meravakeel.app.pojo.Law;
import com.meravakeel.app.pojo.MyGlobalVar;

public class LawUAdapter1 extends FirebaseRecyclerAdapter<Law, LawUAdapter1.myviewholder>
{
    public LawUAdapter1(@NonNull FirebaseRecyclerOptions<Law> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Law model)
    {
        Law l=new Law();
        l.setMainT(model.getMainT());
        l.setSubT(model.getSubT());
        l.setDesc(model.getDesc());
        l.setLawref(model.getLawref());
        l.setWcbd(model.getWcbd());
        holder.mainT.setText(""+model.getMainT());
        holder.subT.setText(""+model.getSubT());

holder.lv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
Intent i=new Intent(view.getContext(), ShowLawDtls.class);
i.putExtra("law",l);
        view.getContext().startActivity(i);
       // Toast.makeText(view.getContext(), "Show Law Activity",Toast.LENGTH_LONG).show();
    }
});





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
