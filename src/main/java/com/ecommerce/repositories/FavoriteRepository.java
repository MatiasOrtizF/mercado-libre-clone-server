package com.ecommerce.repositories;

import com.ecommerce.models.Favorite;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);

    Favorite findByUserAndProduct(User user, Product product);
}
