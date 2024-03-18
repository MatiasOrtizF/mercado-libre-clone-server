package com.ecommerce.services;

import com.ecommerce.exceptions.ProductAlreadyInCartException;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.exceptions.UnauthorizedException;
import com.ecommerce.models.Cart;
import com.ecommerce.models.CartItemRequest;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import com.ecommerce.repositories.CartRepository;
import com.ecommerce.repositories.ProductRepository;
import com.ecommerce.repositories.UserRepository;
import com.ecommerce.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, AuthService authService, JWTUtil jwtUtil, UserRepository userRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Cart> getAllProductsInCart(String token) {
        if(authService.validationToken(token)) {
            String userId = jwtUtil.getKey(token);
            return cartRepository.findByUserId(Long.valueOf(userId));
        } throw new UnauthorizedException("Unauthorized: Invalid token");
    }

    public Cart addProductInCart(Long productId, String token) {
        if(authService.validationToken(token)) {
            String userId = jwtUtil.getKey(token);
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new ResourceNotFoundException("The user is not found"));
            Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("The product is not found"));

            Cart existingCart = cartRepository.findByUserAndProduct(user, product);

            if(existingCart==null) {
                Cart cart = new Cart();
                cart.setQuantity(1);
                cart.setUser(user);
                cart.setProduct(product);
                return cartRepository.save(cart);
            } throw new ProductAlreadyInCartException("Product already in cart");
        } throw new UnauthorizedException("Unauthorized: invalid token");
    }

    public void deleteProductInCart(Long id, String token) {
        if(authService.validationToken(token)) {
            Cart cart = cartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The product with this id: " + id + "is not found"));;
            cartRepository.delete(cart);
        } throw new UnauthorizedException("Unauthorized: invalid token");
    }
}
