package com.example.car.mapper;

import com.example.car.dto.CarOwnerDto;
import com.example.car.entity.Car;
import com.example.car.entity.CarOwner;

import java.util.List;
import java.util.stream.Collectors;

public class CarOwnerMapper implements EntityDtoMapper<CarOwner, CarOwnerDto> {

    @Override
    public CarOwner convertToEntity(CarOwnerDto owner) {
        return CarOwner.builder()
                .id(owner.getId())
                .name(owner.getName())
                .dateStoppedBeingOwner(owner.getDateStoppedBeingOwner())
                .build();
    }

    @Override
    public CarOwnerDto convertToDto(CarOwner owner) {
        return CarOwnerDto.builder()
                .id(owner.getId())
                .name(owner.getName())
                .dateStoppedBeingOwner(owner.getDateStoppedBeingOwner())
                .build();
    }

    @Override
    public List<CarOwnerDto> convertToDtoList(List<CarOwner> list) {
        return list.stream()
                .map(owner -> CarOwnerDto.builder()
                        .id(owner.getId())
                        .name(owner.getName())
                        .dateStoppedBeingOwner(owner.getDateStoppedBeingOwner())
                        .build())
                .collect(Collectors.toList());
    }

    public List<CarOwner> convertToEntityList(List<CarOwnerDto> list, Car car) {
        return list.stream()
                .map(owner -> CarOwner.builder()
                        .id(owner.getId())
                        .name(owner.getName())
                        .dateStoppedBeingOwner(owner.getDateStoppedBeingOwner())
                        .car(car)
                        .build())
                .collect(Collectors.toList());
    }
}
