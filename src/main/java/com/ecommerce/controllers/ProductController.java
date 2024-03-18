package com.ecommerce.controllers;

import com.ecommerce.exceptions.UnauthorizedException;
import com.ecommerce.models.Product;
import com.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:19006/", "192.168.0.9:8081"})
@RequestMapping("api/product")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestHeader(value = "Authorization")String token) {
        try {
            return ResponseEntity.ok(productService.getAllProducts(token));
        } catch (UnauthorizedException e) {
            return ResponseEntity.badRequest().body("Unauthorized: invalid token");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id, @RequestHeader(value = "Authorization")String token) {
        try {
            return ResponseEntity.ok(productService.getProduct(id, token));
        }catch (UnauthorizedException e) {
            return ResponseEntity.badRequest().body("Unauthorized: invalid token");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStockProduct(@PathVariable Long id, @RequestParam Integer quantityPurchased, @RequestHeader(value = "Authorization")String token) {
        try {
            return ResponseEntity.ok(productService.updateStockProduct(id, quantityPurchased, token));
        } catch (UnauthorizedException e) {
            return ResponseEntity.badRequest().body("Unauthorized: invalid token");
        }
    }
}
