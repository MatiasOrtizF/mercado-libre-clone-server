package com.ecommerce.models.vehicles;

import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "motorcycle")
public class Motorcycle {

    // global

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user")
    private User user;

    // motocycle

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

    @Column(name = "weight")
    private Integer weight;
}
