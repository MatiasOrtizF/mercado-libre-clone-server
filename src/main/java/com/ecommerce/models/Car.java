package com.ecommerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car extends Product{
    @Column(name = "doors")
    private Integer doors;

    @Column(name = "fuel_type")
    private String fuelType;

    // Otros campos específicos para autos
}
