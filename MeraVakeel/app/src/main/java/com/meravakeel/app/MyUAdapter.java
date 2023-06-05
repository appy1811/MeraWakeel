package com.meravakeel.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyUAdapter extends RecyclerView.Adapter<MyUAdapter.ViewHolder> {
    private List<MyList> myListList;
    private Context ct;

    public MyUAdapter(List<MyList> myListList, Context ct) {
        this.myListList = myListList;
        this.ct = ct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.myimags,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyList myList=myListList.get(position);
        holder.imageView.setImageDrawable(ct.getResources().getDrawable(myList.getImage()));

        holder.textView.setText(myList.getMyname());
    }

    @Override
    public int getItemCount() {
        return myListList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.myimage);

            textView=(TextView)itemView.findViewById(R.id.mycap);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        //  Toast.makeText(ct.getApplicationContext(), "u clicked"+(textView.getText().toString()),Toast.LENGTH_LONG).show();
                        String cap = textView.getText().toString();
                        if (cap.equals("Logout")) {
                            ((Activity) ct).finish();
                        }
                        if (cap.equals("Abt App")) {
                            ct.startActivity(new Intent(ct.getApplicationContext(), AboutActivity.class));
                        }
                        if (cap.equals("Profile")) {
                            ct.startActivity(new Intent(ct.getApplicationContext(), Profile.class));
                        }
                        if (cap.equals("Lawyer Details")) {
                            ct.startActivity(new Intent(ct.getApplicationContext(), AllULDtls.class));
                        }
                        if (cap.equals("Saved Search")) {
                            ct.startActivity(new Intent(ct.getApplicationContext(), AllUSearch.class));
                        }
                        if (cap.equals("Law Details")) {
                            ct.startActivity(new Intent(ct.getApplicationContext(), AllULawDtls.class));
                        }
                        if (cap.equals("IPC")) {
                            ct.startActivity(new Intent(ct.getApplicationContext(), AllUIpc.class));
                        }
                        if (cap.equals("CRPC")) {
                            ct.startActivity(new Intent(ct.getApplicationContext(), AllUCrpc.class));
                        }

                    }
                    catch(Exception e){
                        AlertDialog alert = new AlertDialog.Builder(ct.getApplicationContext()).create();
                        alert.setTitle("Error");
                        alert.setMessage("Sorry, your device doesn't support flash light!");
                        alert.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((Activity) ct.getApplicationContext()).finish();
                            }
                        });

                        alert.show();

                    }
                }
            });
        }
    }
}

