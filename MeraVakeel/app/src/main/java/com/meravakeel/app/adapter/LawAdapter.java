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
import android.widget.Toast;

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

public class LawAdapter extends FirebaseRecyclerAdapter<Law, LawAdapter.myviewholder>
{
    public LawAdapter(@NonNull FirebaseRecyclerOptions<Law> options)
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





        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.mainT.getContext())
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.dialogcontentlaw))
                        .setExpanded(true,1400)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText mT,sT,wcbd,ref,desc;

                mT = myview.findViewById(R.id.mainT);
                sT = myview.findViewById(R.id.subT);
                wcbd = myview.findViewById(R.id.wcbd);
                ref = myview.findViewById(R.id.ref);
                desc = myview.findViewById(R.id.desc);




                Button submit=myview.findViewById(R.id.basubmit);




                mT.setText(model.getMainT());
                sT.setText(model.getSubT());
                desc.setText(model.getDesc());
                wcbd.setText(model.getWcbd());
                ref.setText(""+model.getLawref());


                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("mainT",mT.getText().toString());
                        //dname, address, mobno, emailid, specialization, totalexp, qualification, Bid, uname, password;
                        map.put("subT",sT.getText().toString());
                        map.put("desc",desc.getText().toString());
                        map.put("wcbd",wcbd.getText().toString());
                        map.put("lawref",ref.getText().toString());

                        if(sT.getText().toString().equals("") || desc.getText().toString().equals("") || wcbd.getText().toString().equals("") || ref.getText().toString().equals(""))
                        {
                            Toast.makeText(desc.getContext(), "Please fill values" ,Toast.LENGTH_LONG);
                            return;
                        }



                        FirebaseDatabase.getInstance().getReference().child("tbllawdtls")
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
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.mainT.getContext());
                builder.setTitle("Delete Law Details");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("tbllawdtls")
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowlaw,parent,false);
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




            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
