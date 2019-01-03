package com.crazyheads.nilami;

import android.util.Log;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import javax.annotation.Nullable;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class FirebaseQueryLiveData extends LiveData<DocumentSnapshot> {

    private final String TAG = "FirebaseQueryLiveData";
 //   private FirebaseFirestore db;
    private final MyEventListner listener = new MyEventListner();
    private DocumentReference documentReference;



    public FirebaseQueryLiveData(DocumentReference ref) {
        this.documentReference = ref;
    }


    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        //add listner
        documentReference.addSnapshotListener(listener);


    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    private class MyOnSuccessListener implements OnSuccessListener<DocumentSnapshot>{

        @Override
        public void onSuccess(DocumentSnapshot documentSnapshot) {
            setValue(documentSnapshot);
        }
    }



    private class MyEventListner implements EventListener<DocumentSnapshot>
    {


        @Override
        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
            if (e != null) {
                Log.w(TAG, "Listen failed.", e);
                return;
            }

            if (documentSnapshot != null && documentSnapshot.exists()) {
                setValue(documentSnapshot);

            } else {
                Log.d(TAG, "Current data: null");
            }
        }
    }



}
