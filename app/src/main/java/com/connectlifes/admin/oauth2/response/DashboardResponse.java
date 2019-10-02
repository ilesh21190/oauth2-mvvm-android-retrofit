package com.connectlifes.admin.oauth2.response;

public class DashboardResponse extends  ErrorResponse {
    String message;
    public DashboardResponse(String error,String errorDescription){
        super(error,errorDescription);
    }
    public DashboardResponse(Throwable t){
        super(t);
    }

    public String getMessage() {
        return message;
    }
}
