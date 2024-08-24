package com.noxfilers.fuelApp.apiRes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiRes<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiRes() {
    }
    public boolean isSuccess() {
        return success;
    }
}

