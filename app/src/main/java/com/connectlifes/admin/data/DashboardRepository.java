package com.connectlifes.admin.data;

import android.content.Context;
import android.util.Log;

import com.connectlifes.admin.oauth2.client.ServiceGenerator;
import com.connectlifes.admin.oauth2.response.DashboardResponse;
import com.connectlifes.admin.oauth2.response.ErrorResponse;
import com.connectlifes.admin.oauth2.service.DashboardService;
import com.connectlifes.admin.ui.dashboard.DashboardListener;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class DashboardRepository {

    private final static String TAG = "Admin-DashboardRepo";

    private static volatile DashboardRepository instance;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private static DashboardService dashboardService;
    // private constructor : singleton access
    private DashboardRepository(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    public static DashboardRepository getInstance(Context context) {
        if (dashboardService== null) {
            dashboardService= ServiceGenerator.ApiService(DashboardService.class,context);
        }
        instance = new DashboardRepository(dashboardService);
        return instance;
    }

    public void getDashboardCount(final DashboardListener listener){

        dashboardService.getDashboardCounts().enqueue(new Callback<DashboardResponse>() {
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
                if (response.code() == 200) {
                    Log.i(TAG, response.body().toString());
                    listener.onSuccess(response.body());
                } else {
                    Gson gson = new Gson();
                    ErrorResponse errorResponse = null;
                    try {
                        errorResponse = gson.fromJson(response.errorBody() != null ? response.errorBody().string() : null, ErrorResponse.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, errorResponse.toString());

                    listener.onError(new DashboardResponse(errorResponse.getError(),errorResponse.getErrorDescription()));
                }

            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {
                t.getStackTrace();
                listener.onError(new DashboardResponse(t));
            }
        });
    }
}

