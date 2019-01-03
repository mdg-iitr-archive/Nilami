package com.crazyheads.nilami;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllAuctionFragment extends Fragment {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_auction_recyclerview,container,false);

        floatingActionButton=(FloatingActionButton)view.findViewById(R.id.AddAuctionRequestButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_allAuction_to_addAuction);
            }
        });

        recyclerView = view.findViewById(R.id.auctions_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL, false));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AllAuctionViewModel allAuctionViewModel = new AllAuctionViewModel();

        LiveData<ArrayList<AuctionProduct>> listLiveData = allAuctionViewModel.getAuctionProductListLiveData();

        listLiveData.observe(getActivity(), new Observer(){

            @Override
            public void onChanged(Object o) {
                recyclerView.setAdapter(new AuctionRecyclerAdapter(o));
            }
        });
    }
}
