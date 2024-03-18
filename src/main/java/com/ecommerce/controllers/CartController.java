package com.ecommerce.controllers;

import com.ecommerce.exceptions.ProductAlreadyInCartException;
import com.ecommerce.exceptions.UnauthorizedException;
import com.ecommerce.repositories.CartRepository;
import com.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@CrossOrigin(origins = {"http://localhost:19006/", "192.168.0.9:8081"})
@RequestMapping("/api/cart")
@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProductsInCart(@RequestHeader(value = "Authorization")String token) {
        try {
            return ResponseEntity.ok(cartService.getAllProductsInCart(token));
        }
        catch (UnauthorizedException e) {
            return ResponseEntity.badRequest().body("Unauthorized: invalid token");
        }
    }

    @PostMapping("{productId}")
    public ResponseEntity<?> addProductInCart(@PathVariable Long productId, @RequestHeader(value = "Authorization")String token) {
        try {
            return ResponseEntity.ok(cartService.addProductInCart(productId, token));
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
            cartService.deleteProductInCart(productId, token);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Product does not exist");
        }catch (UnauthorizedException e) {
            return ResponseEntity.badRequest().body("Unauthorized: invalid token");
        }
    }
}
