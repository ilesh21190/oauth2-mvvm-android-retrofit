package com.connectlifes.admin.ui.lead;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connectlifes.admin.R;
import com.connectlifes.admin.oauth2.response.Leads;

import java.util.ArrayList;

public class LeadFragment extends Fragment {

    private static final String TAG = "Admin-LeadFragment";
    ArrayList<Leads> leadsList = new ArrayList<>();
    private LeadViewModel leadViewModel;
    LeadListAdapter leadListAdapter;
    RecyclerView rvLeads;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        leadViewModel =
                ViewModelProviders.of(this, new LeadViewModelFactory(getActivity().getApplicationContext()))
                        .get(LeadViewModel.class);
        root = inflater.inflate(R.layout.fragment_lead, container, false);
        rvLeads = root.findViewById(R.id.rvLeads);
        findAllLeads();
        setupRecyclerView();
        return root;
    }

    public void findAllLeads() {
        leadViewModel.getLeads();
        leadViewModel.findAllLeads().observe(this, new Observer<ArrayList<Leads>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Leads> leads) {
                if (leads == null) {
                    return;
                } else {
                    leadsList.addAll(leads);
                    Log.i(TAG, "leads >>"+leadsList.size());
                }
                leadListAdapter.notifyDataSetChanged();
            }
        });


    }
    private void setupRecyclerView() {
        Log.i(TAG, "I am setup");
        if (leadListAdapter == null) {
            Log.d(TAG,"I am setting up leadListAdapter ");
            leadListAdapter = new LeadListAdapter(getContext(), leadsList);
            rvLeads.setLayoutManager(new LinearLayoutManager(getContext()));
            rvLeads.setAdapter(leadListAdapter);
            rvLeads.setItemAnimator(new DefaultItemAnimator());
            rvLeads.setNestedScrollingEnabled(true);
        } else {
            leadListAdapter.notifyDataSetChanged();
        }
    }

}