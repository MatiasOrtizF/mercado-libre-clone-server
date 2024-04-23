package com.ecommerce.services;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.exceptions.UnauthorizedException;
import com.ecommerce.models.Product;
import com.ecommerce.repositories.ProductRepository;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AuthService authService;

    @Autowired
    public ProductService(ProductRepository productRepository, AuthService authService) {
        this.productRepository = productRepository;
        this.authService = authService;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The product with this id: " + id + "is not found"));
    }

    public List<Product> searchProductByName(String word) {
        return productRepository.findByTitle(word);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsWithDiscount() {
        return productRepository.findByDiscountPercentageNotNull();
    }


    /*public Product updateStockProduct(Long id, Integer quantityPurchased, String token) {
        if(authService.validationToken(token)) {
            Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The product with this id: " + id + "is not found"));
            product.setStock(product.getStock()-quantityPurchased);

            return productRepository.save(product);
        }
        throw new UnauthorizedException("Unauthorized: invalid token");
    }*/
}
