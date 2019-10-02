package com.connectlifes.admin.oauth2.client;

import android.content.Context;
import android.util.Log;

import com.connectlifes.admin.oauth2.service.UtilityService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class APIHeaderInterceptor implements Interceptor {
    private static final String TAG = "APIHeaderInterceptor";
    Context mContext;
    private String mAuth;
    UtilityService utilityService;
    public APIHeaderInterceptor(Context mContext) {
        this.mContext = mContext;
        utilityService = new UtilityService(this.mContext);
    }



    @Override
    public Response intercept(Chain chain) throws IOException {
        mAuth = utilityService.getAccessTokenFromPreference();
        Request r = chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                // authorization token here
                .addHeader("Authorization", "Bearer " + mAuth)
                .build();

        okhttp3.Response response = chain.proceed(r);
        Log.i(TAG,String.valueOf(response.code()));
        return response;
    }


}
