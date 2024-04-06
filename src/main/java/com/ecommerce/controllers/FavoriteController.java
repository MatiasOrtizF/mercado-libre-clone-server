package com.ecommerce.controllers;

import com.ecommerce.exceptions.ProductAlreadyInCartException;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.exceptions.UnauthorizedException;
import com.ecommerce.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:19006/", "192.168.0.9:8081"})
@RequestMapping("/api/favorite")
@RestController
public class FavoriteController {
    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProductsInCart(@RequestHeader(value = "Authorization")String token) {
        try {
            return ResponseEntity.ok(favoriteService.getAllProductsInCart(token));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: invalid token");
        }
    }

    @PostMapping("{productId}")
    public ResponseEntity<?> addProductInCart(@PathVariable Long productId, @RequestHeader(value = "Authorization")String token) {
        try {
            return ResponseEntity.ok(favoriteService.addProductInCart(productId, token));
        } catch (ProductAlreadyInCartException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product already in favorite");
        }
        catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: invalid token");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProductInFavorite(@PathVariable Long id, @RequestHeader(value = "Authorization")String token) {
        try{
            return ResponseEntity.ok(favoriteService.deleteProductInFavorite(id, token));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product does not exist");
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: invalid token");
        }
    }
}
