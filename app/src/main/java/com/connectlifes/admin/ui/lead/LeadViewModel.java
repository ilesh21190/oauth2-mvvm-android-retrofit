package com.connectlifes.admin.ui.lead;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.connectlifes.admin.data.LeadRepository;
import com.connectlifes.admin.oauth2.response.APIResponse;
import com.connectlifes.admin.oauth2.response.Leads;
import com.connectlifes.admin.oauth2.service.Listener;

import java.util.ArrayList;

public class LeadViewModel extends ViewModel {

    private static final String TAG = "Admin-LeadViewModel";
    private MutableLiveData<ArrayList<Leads>> leadsResult = new MutableLiveData<>();
    private MutableLiveData<Integer> total  = new MutableLiveData<>();
    private LeadRepository leadRepository;

    LeadViewModel(LeadRepository leadRepository) {
        this.leadRepository= leadRepository;
    }

    LiveData<ArrayList<Leads>> findAllLeads() {
        return leadsResult;
    }

    public void getLeads() {
        leadRepository.findAllLeads(new Listener<APIResponse<ArrayList<Leads>>>() {
            @Override
            public void onSuccess(APIResponse<ArrayList<Leads>> apiResponse) {
                Log.i(TAG,apiResponse.getLeads().toString());
                Log.i(TAG, "view model change : >>>"+apiResponse.getLeads().size());
                leadsResult.setValue(apiResponse.getLeads());

            }

            @Override
            public void onError(APIResponse<ArrayList<Leads>> apiResponse) {
                leadsResult.setValue(apiResponse.getLeads());
            }
        });
    }
}