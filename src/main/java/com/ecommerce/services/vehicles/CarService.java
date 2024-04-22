package com.ecommerce.services.vehicles;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.exceptions.UnauthorizedException;
import com.ecommerce.exceptions.UserMismatchException;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import com.ecommerce.models.vehicles.Car;
import com.ecommerce.repositories.ProductRepository;
import com.ecommerce.repositories.UserRepository;
import com.ecommerce.repositories.vehicles.CarRepository;
import com.ecommerce.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CarService(CarRepository carRepository, AuthService authService, UserRepository userRepository,
                       ProductRepository productRepository) {
        this.carRepository = carRepository;
        this.authService = authService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The car is not found"));
    }

    public Car addCar(Car car, String token) {
        if(authService.validationToken(token)) {
            Long userId = authService.getUserId(token);

            Product newProduct = car.getProduct();
            Product product = productRepository.save(newProduct);
            User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("The user is not found"));

            car.setProduct(product);
            car.setUser(user);

            return carRepository.save(car);
        } throw new UnauthorizedException("Unauthorized: invalid token");
    }

    public void deleteCar(Long id, String token) {
        if(authService.validationToken(token)) {
            Long userId = authService.getUserId(token);
            Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The car with this id: " + id + "is not found"));
            if(car.getUser().getId().equals(userId)) {
                carRepository.delete(car);
            } throw new UserMismatchException("User mismatch");
        } throw new UnauthorizedException("Unauthorized: invalid token");
    }
}
