package com.thomasdriscoll.javatemplate.lib.responses;

import lombok.Data;

@Data
public class DriscollResponse <T> {
    private final int status;
    private final T data;

    public DriscollResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }
}