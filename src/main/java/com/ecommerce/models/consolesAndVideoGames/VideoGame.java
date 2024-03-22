package com.ecommerce.models.consolesAndVideoGames;

import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "video_game")
public class VideoGame {

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


    @Column(name = "price")
    private Integer price;

    @Column(name = "colecttion")
    private String collection;

    @Column(name = "title_video_game")
    private String title_video_game;

    @Column(name = "edition")
    private String edition;

    @Column(name = "platform")
    private String platform;

    @Column(name = "developers")
    private String developers;

    @Column(name = "editorial")
    private String editorial;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;
}
