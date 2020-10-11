package com.example.yoga;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BeginnerAdapter extends RecyclerView.Adapter<BeginnerAdapter.BeginnerViewHolder> {


    private Context mctx;
    private List<BeginnerYoga> beginnerList;
    public BeginnerAdapter(Context mctx, List<BeginnerYoga> beginnerList) {
        this.mctx = mctx;
        this.beginnerList = beginnerList;
    }


    @NonNull
    @Override
    public BeginnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.beginner_list,parent,false);
        return new BeginnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeginnerViewHolder holder, int position) {
        final  BeginnerYoga beginner=beginnerList.get(position);
        holder.name.setText(beginner.getName());
        holder.level.setText(beginner.getLevel());
        holder.kcal.setText(beginner.getKcal());
        holder.time.setText(beginner.getTime());
        holder.imageView.setImageResource(beginner.getImage());

        String title=beginner.getName();



    }

    @Override
    public int getItemCount() {
        return beginnerList.size();
    }

    class BeginnerViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView name,time,level,kcal;

        public BeginnerViewHolder(@NonNull View itemView) {
            super(itemView);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(),Mountain.class);
                    i.putExtra("title",String.valueOf(beginnerList.get(getAdapterPosition())));

                    v.getContext().startActivity(i);
                }
            });
            name=itemView.findViewById(R.id.beginner_name);
            time=itemView.findViewById(R.id.beginner_time);
            level=itemView.findViewById(R.id.beginner_level);
            kcal=itemView.findViewById(R.id.beginner_kcal);
            imageView=itemView.findViewById(R.id.beginner_image);
        }
    }
}
