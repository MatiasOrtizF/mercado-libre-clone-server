package com.ecommerce.repositories;

import com.ecommerce.models.Cart;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);

    Cart findByUserAndProduct(User user, Product product);
}
