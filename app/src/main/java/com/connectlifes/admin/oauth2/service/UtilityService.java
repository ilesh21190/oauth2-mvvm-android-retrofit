package com.connectlifes.admin.oauth2.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.connectlifes.admin.oauth2.constant.OauthConstant;
import com.connectlifes.admin.oauth2.response.AccessTokenResponse;

public class UtilityService {
    private SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private static final  String TAG = UtilityService.class.getName();
    public UtilityService(Context appContext) {
        prefs = appContext.getSharedPreferences(OauthConstant.APPLICATION_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveAccessTokenToPreference(AccessTokenResponse accessTokenResponse){
        Log.i(TAG, accessTokenResponse.toString());
        editor.putString("AccessToken", accessTokenResponse.getAccessToken());
        editor.putString("refresh", accessTokenResponse.getRefreshToken());
        editor.putLong("expiry", accessTokenResponse.getExpiry());
        editor.commit();

    }

    public String getAccessTokenFromPreference() {
        return prefs.getString("AccessToken","");
    }
}
