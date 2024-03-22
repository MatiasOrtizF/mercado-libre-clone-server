package com.ecommerce.repositories.vehicles;

import com.ecommerce.models.vehicles.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
