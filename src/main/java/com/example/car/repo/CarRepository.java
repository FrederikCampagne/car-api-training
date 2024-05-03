package com.example.car.repo;

import com.example.car.entity.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends CrudRepository<Car, UUID> {
    List<Car> findByBrand(String brand);
}
