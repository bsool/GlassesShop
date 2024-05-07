package com.bsool.glasses.glassesshop.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"stackTrace", "cause", "localizedMessage", "suppressed", "causeException"})
public class GlassesShopException extends RuntimeException {

    private String errorMessage;
    public GlassesShopException(final String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
}
