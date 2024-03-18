package com.ecommerce.models;

import lombok.Data;

@Data
public class CartItemRequest {

    private User user;
    private Product product;
}
