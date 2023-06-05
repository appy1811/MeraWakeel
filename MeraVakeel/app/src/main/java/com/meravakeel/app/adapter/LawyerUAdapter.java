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
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.meravakeel.app.R;
import com.meravakeel.app.pojo.Lawyer;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;
import java.util.Map;

public class LawyerUAdapter extends FirebaseRecyclerAdapter<Lawyer, LawyerUAdapter.myviewholder>
{
    public LawyerUAdapter(@NonNull FirebaseRecyclerOptions<Lawyer> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Lawyer model)
    {

        holder.dname.setText("Name: "+model.getDname());
        holder.add.setText("Address: "+model.getAddress());
        holder.mobno.setText("Mobile No: "+model.getMobno());
        holder.email.setText("EmailID: "+model.getEmailid());
        holder.spc.setText("Specialization: "+model.getSpecialization());

                holder.totexp.setText("Experiance in Year: "+model.getTotalexp());
                holder.qual.setText("Qualification: "+model.getQualification());





    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdru,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder


    {

        ImageView edit,delete;
        TextView dname, add, mobno, email,spc,
                totexp, qual, uname,bn;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            dname = itemView.findViewById(R.id.dname);
            add = itemView.findViewById(R.id.dadd);
            mobno = itemView.findViewById(R.id.dmobno);
            email = itemView.findViewById(R.id.demailid);
            spc = itemView.findViewById(R.id.specialization);
            qual = itemView.findViewById(R.id.qualification);
            totexp = itemView.findViewById(R.id.totalexp);




        }
    }
}
