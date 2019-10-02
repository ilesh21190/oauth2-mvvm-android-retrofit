package com.connectlifes.admin.oauth2.client;

import com.connectlifes.admin.oauth2.constant.OauthConstant;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OauthHeaderInteceptor implements Interceptor {
    private String credentials;

    public OauthHeaderInteceptor() {
        this.credentials = Credentials.basic(OauthConstant.CLIENT_ID,OauthConstant.CLIENT_SECRET);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }
}
