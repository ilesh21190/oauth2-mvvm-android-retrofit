package com.connectlifes.admin.oauth2.request;

import com.connectlifes.admin.oauth2.constant.OauthConstant;

public class AccessTokenRequest {
    private String username;
    private String password;
    private String client_id;
    private String client_secret;
    private String grant_type;
    private String refresh_token;

    public AccessTokenRequest(String username, String password, String grant_type) {
        this.username = username;
        this.password = password;
        this.grant_type = grant_type;
        this.client_id = OauthConstant.CLIENT_ID;
        this.client_secret = OauthConstant.CLIENT_SECRET;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
