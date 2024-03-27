package com.ecommerce.controllers.vehicles;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.exceptions.UnauthorizedException;
import com.ecommerce.exceptions.UserMismatchException;
import com.ecommerce.models.vehicles.Car;
import com.ecommerce.models.vehicles.CarRequest;
import com.ecommerce.services.vehicles.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@CrossOrigin(origins = {"http://localhost:19006/", "192.168.0.9:8081",})
@RequestMapping("/api/product/car")
@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCars() {
        try {
            return ResponseEntity.ok(carService.getAllCars());
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: invalid token");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(carService.getCar(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car does not exist");
        }
    }

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody Car car, @RequestHeader(value = "Authorization")String token) {
        try {
            return ResponseEntity.ok(carService.addCar(car, token));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: invalid token");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id, @RequestHeader(value = "Authorization")String token) {
        try{
            carService.deleteCar(id, token);
            return ResponseEntity.ok().build();
        } catch (UserMismatchException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User mismatch");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car does not exist");
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: invalid token");
        }
    }
}