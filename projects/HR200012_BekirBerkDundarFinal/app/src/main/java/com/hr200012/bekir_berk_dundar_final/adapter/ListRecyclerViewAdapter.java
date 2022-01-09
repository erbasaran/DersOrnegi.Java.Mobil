package com.hr200012.bekir_berk_dundar_final.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hr200012.bekir_berk_dundar_final.R;
import com.hr200012.bekir_berk_dundar_final.model.Footballer;
import com.hr200012.bekir_berk_dundar_final.virtual.ClickListener;

import java.util.List;

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.MyViewHolder> {
    private List<Footballer> footballerList;
    private ClickListener<Footballer> clickListener;
    private Context context;

    public ListRecyclerViewAdapter(List<Footballer> footballerList) {
        this.footballerList = footballerList;
    }

    @Override
    public ListRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_list_item, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListRecyclerViewAdapter.MyViewHolder holder, final int position) {
        final Footballer footballer = footballerList.get(position);
        holder.name.setText(footballer.name);
        holder.team.setText(footballer.team);
        Glide.with(context)
                .load(footballer.avatar)
                .into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(footballer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return footballerList.size();
    }

    public void setOnItemClickListener(ClickListener<Footballer> clickListener) {
        this.clickListener = clickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, team;
        private ImageView image;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            team = itemView.findViewById(R.id.txtTeam);
            image = itemView.findViewById(R.id.imgAvatar);
            cardView = itemView.findViewById(R.id.recyclerCardView);
        }
    }
}