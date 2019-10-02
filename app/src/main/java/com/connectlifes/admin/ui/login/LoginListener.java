package com.connectlifes.admin.ui.login;

import com.connectlifes.admin.oauth2.response.AccessTokenResponse;

public interface LoginListener {
    void onSuccess(AccessTokenResponse accessTokenResponse);
    void onError(AccessTokenResponse accessTokenResponse);
}
