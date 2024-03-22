package com.ecommerce.models.vehicles;

import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import lombok.Data;

@Data
public class CarRequest {
    private Car car;
    private Product product;
}

