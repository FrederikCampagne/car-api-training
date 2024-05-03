package com.example.car.repo;

import com.example.car.entity.CarOwner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CarOwnerRepository extends CrudRepository<CarOwner, UUID> {
    List<CarOwner> findByCarId(UUID id);

    CarOwner findByName(String name);
}
