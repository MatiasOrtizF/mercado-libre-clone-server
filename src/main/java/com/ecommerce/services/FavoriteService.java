package com.ecommerce.services;

import com.ecommerce.exceptions.ProductAlreadyInCartException;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.exceptions.UnauthorizedException;
import com.ecommerce.models.Favorite;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import com.ecommerce.repositories.FavoriteRepository;
import com.ecommerce.repositories.ProductRepository;
import com.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository, AuthService authService, UserRepository userRepository,
                       ProductRepository productRepository) {
        this.favoriteRepository = favoriteRepository;
        this.authService = authService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Favorite> getAllProductsInCart(String token) {
        if(authService.validationToken(token)) {
            Long userId = authService.getUserId(token);
            return favoriteRepository.findByUserId(userId);
        } throw new UnauthorizedException("Unauthorized: Invalid token");
    }

    public Favorite addProductInCart(Long productId, String token) {
        if(authService.validationToken(token)) {
            Long userId = authService.getUserId(token);
            User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("The user is not found"));
            Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("The product is not found"));

            Favorite existingFav = favoriteRepository.findByUserAndProduct(user, product);

            if(existingFav==null) {
                Favorite favorite = new Favorite();
                favorite.setUser(user);
                favorite.setProduct(product);
                return favoriteRepository.save(favorite);
            } throw new ProductAlreadyInCartException("Product already in cart");
        } throw new UnauthorizedException("Unauthorized: invalid token");
    }

    public boolean deleteProductInFavorite(Long id, String token) {
        if(authService.validationToken(token)) {
            Favorite favorite = favoriteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The product with this id: " + id + "is not found"));;
            favoriteRepository.delete(favorite);
            return true;
        } else {
            throw new UnauthorizedException("Unauthorized: invalid token");
        }
    }

    public boolean getFavoriteByProductId(Long productId, String token) {
        if(authService.validationToken(token)) {
            Long userId = authService.getUserId(token);
            User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("The user is not found"));
            Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("The product is not found"));

            Favorite existingFav = favoriteRepository.findByUserAndProduct(user, product);

            return (existingFav != null);
        } else {
            return false;
        }
    }
}
