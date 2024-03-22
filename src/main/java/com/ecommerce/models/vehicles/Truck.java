package com.ecommerce.models.vehicles;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "truck")
public class Truck {
    // global

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "image")
    private String image;

    // car

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "version")
    private String version;

    @Column(name = "kilometres")
    private Integer kilometres;

    @Column(name = "age")
    private Integer age;

    @Column(name = "color")
    private String color;

    @Column(name = "payload_capacity")
    private String payloadCapacity;

    @Column(name = "transmission")
    private String transmission;
}
