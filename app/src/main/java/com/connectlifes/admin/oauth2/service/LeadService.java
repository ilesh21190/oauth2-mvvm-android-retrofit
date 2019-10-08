package com.connectlifes.admin.oauth2.service;

import com.connectlifes.admin.oauth2.response.APIResponse;
import com.connectlifes.admin.oauth2.response.Leads;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeadService {
    @GET("lead/v1/get")
    Call<APIResponse<ArrayList<Leads>>> findAllLeads();
}
