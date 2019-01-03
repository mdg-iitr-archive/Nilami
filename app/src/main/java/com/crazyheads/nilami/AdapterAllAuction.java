package com.crazyheads.nilami;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterAllAuction extends RecyclerView.Adapter<AdapterAllAuction.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.auction_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView product_name;
        public TextView min_price;
        public TextView auction_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name=(TextView)itemView.findViewById(R.id.product_name);
            min_price=(TextView)itemView.findViewById(R.id.min_price);
            auction_date=(TextView)itemView.findViewById(R.id.auction_date);
        }
    }
}
