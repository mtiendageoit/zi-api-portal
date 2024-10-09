package com.zonainmueble.exceptions;

import lombok.*;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private String code;
    private String message;

    public BaseException() {
        this.code = "UNKOWN_ERROR";
        this.message = "Unkown error";
    }
}
