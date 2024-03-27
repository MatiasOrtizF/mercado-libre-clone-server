package com.ecommerce.controllers;

import com.ecommerce.exceptions.ProductAlreadyInCartException;
import com.ecommerce.exceptions.UnauthorizedException;
import com.ecommerce.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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
        }
        catch (UnauthorizedException e) {
            return ResponseEntity.badRequest().body("Unauthorized: invalid token");
        }
    }

    @PostMapping("{productId}")
    public ResponseEntity<?> addProductInCart(@PathVariable Long productId, @RequestHeader(value = "Authorization")String token) {
        try {
            return ResponseEntity.ok(favoriteService.addProductInCart(productId, token));
        } catch (ProductAlreadyInCartException e) {
            return ResponseEntity.badRequest().body("Product already in cart");
        }
        catch (UnauthorizedException e) {
            return ResponseEntity.badRequest().body("Unauthorized: invalid token");
        }
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProductInCart(@PathVariable Long productId, @RequestHeader(value = "Authorization")String token) {
        try{
            favoriteService.deleteProductInCart(productId, token);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Product does not exist");
        }catch (UnauthorizedException e) {
            return ResponseEntity.badRequest().body("Unauthorized: invalid token");
        }
    }
}
