package com.meravakeel.app.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meravakeel.app.R;
import com.meravakeel.app.pojo.Lawyer;
import com.orhanobut.dialogplus.DialogPlus;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LawyerAdapter extends FirebaseRecyclerAdapter<Lawyer, LawyerAdapter.myviewholder>
{
    public LawyerAdapter(@NonNull FirebaseRecyclerOptions<Lawyer> options)
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



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "click edit",Toast.LENGTH_LONG).show();
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.dname.getContext())
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.dialogcontentlawyer))
                        .setExpanded(true,1600)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText  dname, add, mobno, email,spc,
                        totexp, qual, uname;
                final  Spinner bn;
                dname = myview.findViewById(R.id.dname);
                add = myview.findViewById(R.id.dadd);
                mobno = myview.findViewById(R.id.dmobno);
                email = myview.findViewById(R.id.demailid);
                spc = myview.findViewById(R.id.specialization);
                qual = myview.findViewById(R.id.qualification);
                totexp = myview.findViewById(R.id.totalexp);


                Button submit=myview.findViewById(R.id.basubmit1);
             //   dialogPlus.show();



                dname.setText(model.getDname());
                add.setText(model.getAddress());
                mobno.setText(model.getMobno());
                email.setText(model.getEmailid());
                spc.setText(model.getSpecialization());

                totexp.setText(model.getTotalexp());
                qual.setText(model.getQualification());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("dname",dname.getText().toString());
                        //dname, address, mobno, emailid, specialization, totalexp, qualification, Bid, uname, password;
                        map.put("address",add.getText().toString());
                        map.put("mobno",mobno.getText().toString());
                        map.put("emailid",email.getText().toString());
                        map.put("specialization",spc.getText().toString());
                        map.put("totalexp",totexp.getText().toString());
                        map.put("qualification",qual.getText().toString());
                        if(add.getText().toString().equals("") || dname.getText().toString().equals("") || mobno.getText().toString().equals("") || email.getText().toString().equals("") || spc.getText().toString().equals("") || qual.getText().toString().equals("")  )
                        {
                            Toast.makeText(add.getContext(), "Please fill values" ,Toast.LENGTH_LONG);
                            return;
                        }


                        if (!validatePhno(add.getContext(),mobno.getText().toString()))
                        {
                            return;
                        }

                        FirebaseDatabase.getInstance().getReference().child("tbllawyerdtls")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.dname.getContext());
                builder.setTitle("Delete Lawyer");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("tbllawyerdtls")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdr,parent,false);
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



            edit=(ImageView)itemView.findViewById(R.id.editicon1);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }

    private boolean validatePhno(Context c,String passwordInput) {
         Pattern pattern = Pattern.compile("\\d{10}");

        Matcher matcher = pattern.matcher(passwordInput);
        if(!matcher.matches())
        {
            Toast.makeText(c, "Mobile No should contain 10 digits", Toast.LENGTH_SHORT).show();
            return false;

        }
        else {
            return true;
        }
    }
}
