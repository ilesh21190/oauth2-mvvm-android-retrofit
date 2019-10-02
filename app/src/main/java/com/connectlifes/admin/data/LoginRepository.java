package com.connectlifes.admin.data;

import android.util.Log;

import com.connectlifes.admin.oauth2.client.ServiceGenerator;
import com.connectlifes.admin.oauth2.response.AccessTokenResponse;
import com.connectlifes.admin.oauth2.response.ErrorResponse;
import com.connectlifes.admin.oauth2.service.OauthService;
import com.connectlifes.admin.ui.login.LoginListener;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private final static String TAG = "Admin-LoginRepository";

    private static volatile LoginRepository instance;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private static OauthService oauthService;
    // private constructor : singleton access
    private LoginRepository(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    public static LoginRepository getInstance() {
        if (oauthService == null) {
            oauthService = ServiceGenerator.oAuthService(OauthService.class);
        }
        instance = new LoginRepository(oauthService);
        return instance;
    }

    public void login(String username, String password , final LoginListener listener){
        // handle login
        Log.d(TAG, "Finally inside Repository");

            oauthService.getAccessToken(username, password, "password", "life_admin").enqueue(new Callback<AccessTokenResponse>() {
                @Override
                public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
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

                        listener.onError(new AccessTokenResponse(errorResponse.getError(),errorResponse.getErrorDescription()));
                    }

                }

                @Override
                public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                    t.getStackTrace();
                    listener.onError(new AccessTokenResponse(t));
                }
            });
        }
    }

