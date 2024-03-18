package com.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductAlreadyInCartException extends RuntimeException{
    private static final Long serialVersionUID = 1L;

    public ProductAlreadyInCartException(String message) {
        super(message);
    }
}
