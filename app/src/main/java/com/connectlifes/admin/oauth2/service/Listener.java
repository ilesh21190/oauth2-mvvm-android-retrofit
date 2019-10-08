package com.connectlifes.admin.oauth2.service;

public interface Listener<T> {
    void onSuccess(T response);
    void onError(T error);
}
