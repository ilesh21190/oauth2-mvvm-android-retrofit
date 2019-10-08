package com.connectlifes.admin.oauth2.response;

public class APIResponse<T> extends  ErrorResponse{
    int total;
    T data;
    T leads;

    public APIResponse() {
        super();
    }
    public APIResponse(String error,String errorDescription) {
        super(error,errorDescription);
    }
    public APIResponse(Throwable t) {
        super(t);
    }
    public int getTotal() {
        return total;
    }

    public T getData() {
        return data;
    }

    public T getLeads() {
        return leads;
    }
}
