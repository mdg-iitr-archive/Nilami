package com.crazyheads.nilami;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class AllAuctionViewModel extends ViewModel {
    private final DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("products");
    private FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(productRef);

    private LiveData<ArrayList<AuctionProduct>> auctionProductListLiveData = Transformations.map(liveData,new Deserializer());

    private class Deserializer implements Function<DataSnapshot, ArrayList<AuctionProduct>>{

        @Override
        public ArrayList<AuctionProduct> apply(DataSnapshot input) {
            ArrayList<AuctionProduct> list = new ArrayList<>();
            for (DataSnapshot snapshot : input.getChildren()){
                list.add(input.getValue(AuctionProduct.class));
            }

            return list;
        }
    }

    public LiveData<ArrayList<AuctionProduct>> getAuctionProductListLiveData(){
        return auctionProductListLiveData;
    }
}
