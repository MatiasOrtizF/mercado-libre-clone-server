package com.ecommerce.models.computers;

import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notebook")
public class Notebook {

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

    @Column(name = "processor_type")
    private String processorType;

    @Column(name = "hard_disk_size")
    private Integer hardDiskSize;

    @Column(name = "processor_brand")
    private String processorBrand;

    @Column(name = "processor_line")
    private String processorLine;

    @Column(name = "ram")
    private Integer ram;

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

    @Column(name = "resolution_screen")
    private String resolutionScreen;

    @Column(name = "size_screen")
    private Integer sizeScreen;
}
