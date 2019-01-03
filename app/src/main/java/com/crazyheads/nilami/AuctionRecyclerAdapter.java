package com.crazyheads.nilami;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class AuctionRecyclerAdapter extends RecyclerView.Adapter<AuctionRecyclerAdapter.MyViewHolder> {

    ArrayList<AuctionProduct> listProduct;

    public AuctionRecyclerAdapter(Object o) {

        listProduct = (ArrayList<AuctionProduct>) o;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.auction_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AuctionProduct product = listProduct.get(position);

        holder.setName(product.getProductName());
        holder.setDesc(product.getMinBid());
        holder.setDate();
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView desc;
        TextView date;

        public void setDesc(int description) {
            desc.setText(String.valueOf(description));
        }

        public void setDate() {
            date.setText("Date");
        }

        public void setName(String productName){
            name.setText(productName);
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            desc = itemView.findViewById(R.id.min_price);
            date = itemView.findViewById(R.id.auction_date);
        }
    }
}
