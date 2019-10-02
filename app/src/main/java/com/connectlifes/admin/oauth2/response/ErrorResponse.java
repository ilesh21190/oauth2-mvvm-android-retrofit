package com.connectlifes.admin.oauth2.response;

public class ErrorResponse {
    private String error;
    private String error_description;
    Throwable t;

    public ErrorResponse(){

    }
    public ErrorResponse(String error, String errorDescription) {
        this.error = error;
        this.error_description = errorDescription;
    }
    public ErrorResponse(Throwable t){
        this.t = t;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return error_description;
    }

    public Throwable getT() {
        return t;
    }

    public void setT(Throwable t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error='" + error + '\'' +
                ", errorDescription='" + error_description + '\'' +
                '}';
    }
}
