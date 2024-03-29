package com.ecommerce.repositories;

import com.ecommerce.models.Cart;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);

    Cart findByUserAndProduct(User user, Product product);
}
