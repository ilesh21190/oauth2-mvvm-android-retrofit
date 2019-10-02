package com.connectlifes.admin.oauth2.service;

import com.connectlifes.admin.oauth2.request.AccessTokenRequest;
import com.connectlifes.admin.oauth2.response.AccessTokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OauthService {
    @POST("oauth/token")
    @FormUrlEncoded
    void getAccessToken(AccessTokenRequest accessTokenRequest,
                        Callback<AccessTokenResponse> responseCallback);

    @POST("oauth/token")
    @FormUrlEncoded
    Call<AccessTokenResponse> getAccessToken( @Field("username") String username,
                                              @Field("password") String password,
                                              @Field("grant_type") String grantType,
                                              @Field("source") String source);
}
