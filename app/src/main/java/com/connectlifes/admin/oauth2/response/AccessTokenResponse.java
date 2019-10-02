package com.connectlifes.admin.oauth2.response;

public class AccessTokenResponse {
    private String access_token;
    private String token_type;
    private Integer expires_in;
    private String refresh_token;
    private String scope;
    private String client_id;
    private String client_secret;
    private String error;
    private String error_description;
    private String name;
    Throwable t;

    public AccessTokenResponse(Throwable t) {
        this.t = t;
    }

    public AccessTokenResponse(String name) {
        this.name = name;
    }

    public AccessTokenResponse(String error, String error_description) {
        this.error = error;
        this.error_description = error_description;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public String getTokenType() {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if(!Character.isUpperCase(token_type.charAt(0))) {
            token_type = Character.toString(token_type.charAt(0)).toUpperCase() + token_type.substring(1);
        }

        return token_type;
    }

    public void setTokenType(String token_type) {
        this.token_type = token_type;
    }

    public int getExpiry() {
        return expires_in;
    }

    public void setExpiry(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClientID() {
        return client_id;
    }

    public void setClientID(String client_id) {
        this.client_id = client_id;
    }

    public String getClientSecret() {
        return client_secret;
    }

    public void setClientSecret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public Throwable getT() {
        return t;
    }

    public void setT(Throwable t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "AccessTokenResponse{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", scope='" + scope + '\'' +
                ", client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                '}';
    }
}

