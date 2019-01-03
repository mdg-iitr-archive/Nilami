package com.crazyheads.nilami;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class AddAuctionFragment extends Fragment {

    FloatingActionButton BackButton;
    FloatingActionButton AddButton;
    EditText productName,productDesc,minBid;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_addauction,container,false);


        productName=v.findViewById(R.id.add_product_name);
        productDesc=v.findViewById(R.id.add_product_desc);
        minBid=v.findViewById(R.id.add_min_bid);

        BackButton=(FloatingActionButton)v.findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_addAuction_to_allAuction);


            }
        });

        AddButton=(FloatingActionButton)v.findViewById(R.id.AddAuctionButton);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AuctionProduct product=new AuctionProduct(productName.getText().toString(),productDesc.getText().toString(),Integer.parseInt( minBid.getText().toString()),null,null,null,null,0);

                FirebaseDatabase.getInstance().getReference().child("products").push().setValue(product);
            }
        });



        return v;

    }



}
