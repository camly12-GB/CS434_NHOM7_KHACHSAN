package com.example.thangcachep.demo.model.response;


import lombok.Getter;

import java.io.Serializable;

@Getter
public class DataResponse<T> implements Serializable {
    private final int status;
    private final String message;
    private  T data;

    public DataResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public DataResponse(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }


}
