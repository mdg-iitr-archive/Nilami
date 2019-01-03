package com.crazyheads.nilami;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class AllAuctionViewModel extends ViewModel {
    private static final DocumentReference products =
            FirebaseFirestore.getInstance().collection("products").document();


    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(products);

  //  private final LiveData<AuctionProduct> AuctionProductLiveData =
         //   Transformations.map(liveData,DocumentSnapshot);



    @NonNull
    public LiveData<DocumentSnapshot> getQuerySnapshotLiveData() {
        return liveData;
    }
}
