package com.connectlifes.admin.oauth2.service;

import com.connectlifes.admin.oauth2.response.DashboardResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DashboardService {
    @GET("me")
    Call<DashboardResponse> getDashboardCounts();
}
