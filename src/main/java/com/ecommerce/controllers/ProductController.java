package com.ecommerce.controllers;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.exceptions.UnauthorizedException;
import com.ecommerce.models.Product;
import com.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*@PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        return ResponseEntity.ok(productService.addCar(car));
    }*/


    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.getProduct(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product does not exist");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProductByName(@RequestParam String word) {
        return ResponseEntity.ok(productService.searchProductByName(word));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/discount")
    public ResponseEntity<?> getProductsWithDiscount() {
        return ResponseEntity.ok(productService.getProductsWithDiscount());
    }

    /*@PutMapping("{id}")
    public ResponseEntity<?> updateStockProduct(@PathVariable Long id, @RequestParam Integer quantityPurchased, @RequestHeader(value = "Authorization")String token) {
        try {
            return ResponseEntity.ok(productService.updateStockProduct(id, quantityPurchased, token));
        } catch (UnauthorizedException e) {
            return ResponseEntity.badRequest().body("Unauthorized: invalid token");
        }
    }*/
}
