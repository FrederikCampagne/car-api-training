package com.example.car.mapper;

import com.example.car.dto.CarDto;
import com.example.car.entity.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper implements EntityDtoMapper<Car, CarDto> {

    CarOwnerMapper carOwnerMapper = new CarOwnerMapper();

    @Override
    public CarDto convertToDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .licensePlate(car.getLicensePlate())
                .firstRegistration(car.getFirstRegistration())
                .currentOwner(car.getCurrentOwner())
                .previousOwners(carOwnerMapper
                        .convertToDtoList(car.getPreviousOwners()))
                .build();
    }

    @Override
    public List<CarDto> convertToDtoList(List<Car> cars) {
        return cars.stream()
                .map(car -> CarDto.builder()
                        .id(car.getId())
                        .brand(car.getBrand())
                        .licensePlate(car.getLicensePlate())
                        .firstRegistration(car.getFirstRegistration())
                        .currentOwner(car.getCurrentOwner())
                        .previousOwners(carOwnerMapper
                                .convertToDtoList(car.getPreviousOwners()))
                        .build())
                .collect(Collectors.toList());
    }

    public Car convertToEntity(CarDto car) {
        Car carNew = Car.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .licensePlate(car.getLicensePlate())
                .firstRegistration(car.getFirstRegistration())
                .currentOwner(car.getCurrentOwner()).build();
        carNew.setPreviousOwners(carOwnerMapper
                .convertToEntityList(car.getPreviousOwners(), carNew));
        return carNew;
    }
}
