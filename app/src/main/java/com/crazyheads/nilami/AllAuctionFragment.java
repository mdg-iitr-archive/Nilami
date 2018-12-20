package com.crazyheads.nilami;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AllAuctionFragment extends Fragment {

    private AllAuctionListner listner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_auction_recyclerviwe,container,false);
        return view;
    }

    public void openAddAuction(View view) {

        listner.requestForAddAuctionFragment();
    }

    public interface AllAuctionListner{
        void requestForAddAuctionFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof AllAuctionListner)
        {
            listner=(AllAuctionListner)context;
        }else{
            throw new RuntimeException(context.toString()+" must implement AllAuctionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listner=null;
    }
}
