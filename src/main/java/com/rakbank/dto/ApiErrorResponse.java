package com.rakbank.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ApiErrorResponse implements Serializable {

    private final Object status;

    private final int code;

    private final String message;

    @JsonCreator
    public ApiErrorResponse(@JsonProperty("status") String status,
                            @JsonProperty("code") int code,
                            @JsonProperty("message") String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
