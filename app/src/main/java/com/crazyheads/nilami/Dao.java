package com.crazyheads.nilami;

import com.google.firebase.firestore.FirebaseFirestore;

public class Dao {

    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    public void insert (AuctionProduct auctionProduct){
        db.collection("products").add(auctionProduct);
    }
}
