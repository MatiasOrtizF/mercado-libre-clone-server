package com.ecommerce.models.consolesAndVideoGames;

import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "console")
public class Console {

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

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "edition")
    private String edition;

    @Column(name = "color")
    private String color;

    @Column(name = "processor_brand")
    private String processorBrand;

    @Column(name = "processor_line")
    private String processorLine;

    @Column(name = "ram")
    private Integer ram;

    @Column(name = "storage_capacity")
    private Integer storageCapacity;

    @Column(name = "name_os")
    private String nameOs;

    @Column(name = "version_os")
    private String versionOs;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "width")
    private Integer width;

    @Column(name = "heigth")
    private Integer height;

    // in sec
    @Column(name = "battery_duration")
    private Integer batteryDuration;
}
