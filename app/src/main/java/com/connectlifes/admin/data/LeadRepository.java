package com.connectlifes.admin.data;

import android.content.Context;
import android.util.Log;

import com.connectlifes.admin.oauth2.client.ServiceGenerator;
import com.connectlifes.admin.oauth2.response.APIResponse;
import com.connectlifes.admin.oauth2.response.ErrorResponse;
import com.connectlifes.admin.oauth2.response.Leads;
import com.connectlifes.admin.oauth2.service.LeadService;
import com.connectlifes.admin.oauth2.service.Listener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeadRepository {
    private final static String TAG = "Admin-LeadRepository";

    private static volatile LeadRepository instance;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private static LeadService leadService;
    // private constructor : singleton access
    private LeadRepository(LeadService leadService) {
        this.leadService = leadService;
    }

    public static LeadRepository getInstance(Context context) {
        if (leadService== null) {
            leadService= ServiceGenerator.ApiService(LeadService.class,context);
        }
        instance = new LeadRepository(leadService);
        return instance;
    }

    public void findAllLeads(final Listener<APIResponse<ArrayList<Leads>>> listener){

        leadService.findAllLeads().enqueue(new Callback<APIResponse<ArrayList<Leads>>>() {
            @Override
            public void onResponse(Call<APIResponse<ArrayList<Leads>>> call, Response<APIResponse<ArrayList<Leads>>> response) {
                if (response.code() == 200) {
                    Log.i(TAG, response.body().toString());
                    listener.onSuccess(response.body());
                } else {
                    Gson gson = new Gson();
                    ErrorResponse errorResponse = null;
                    Log.e(TAG, response.errorBody().toString());
                    try {
                        errorResponse = gson.fromJson(response.errorBody() != null ? response.errorBody().string() : null, ErrorResponse.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, errorResponse.toString());

                    listener.onError(new APIResponse<ArrayList<Leads>>(errorResponse.getError(),errorResponse.getErrorDescription()));
                }

            }

            @Override
            public void onFailure(Call<APIResponse<ArrayList<Leads>>> call, Throwable t) {
                t.getStackTrace();
                listener.onError(new APIResponse<ArrayList<Leads>>(t));
            }
        });
    }

}
