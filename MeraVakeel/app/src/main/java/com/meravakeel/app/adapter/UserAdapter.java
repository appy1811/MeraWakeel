package com.meravakeel.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.meravakeel.app.R;
import com.meravakeel.app.pojo.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.HeroViewHolder> {


    private List<User> heroList;
    private Context context;

    private static int currentPosition = 0;

    public UserAdapter(List<User> heroList, Context context) {

        this.heroList = heroList;
        this.context = context;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_dr, parent, false);
        return new HeroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HeroViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        User hero = heroList.get(position);
        holder.textViewName.setText("User :"+hero.getFullname());
        holder.textViewRealName.setText(hero.getFullname());
        holder.textmn.setText(hero.getPhno());
        holder.textem.setText(hero.getEmailid());


        holder.linearLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewRealName, textmn, textViewFirstAppearance,
                textViewCreatedBy, textem, textViewBio;
        ImageView imageView;
        LinearLayout linearLayout;

        HeroViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewRealName = itemView.findViewById(R.id.textViewRealName);
            textmn = itemView.findViewById(R.id.textMN);
            textem=itemView.findViewById(R.id.textem);


            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}