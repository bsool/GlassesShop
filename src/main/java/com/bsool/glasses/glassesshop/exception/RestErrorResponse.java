package com.bsool.glasses.glassesshop.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class RestErrorResponse {
    private String errorCode;
    private String errorMessage;
}
