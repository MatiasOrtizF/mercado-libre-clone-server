package com.ecommerce.models.computers;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pc_desktop")
public class PcDesktop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
}
