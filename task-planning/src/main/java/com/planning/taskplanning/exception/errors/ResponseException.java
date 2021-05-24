package com.planning.taskplanning.exception.errors;


import javax.servlet.http.HttpServletRequest;

public class ResponseException {
    private String method;
    private String path;
    private String errorMessage;
    private Object errorDetail;

    public ResponseException(HttpServletRequest httpServletRequest, String errorMessage, Object errorDetail) {
        this.method = httpServletRequest.getMethod();
        this.path = httpServletRequest.getRequestURI();
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(Object errorDetail) {
        this.errorDetail = errorDetail;
    }
}
