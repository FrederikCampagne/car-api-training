package com.example.car.service;

import com.example.car.dto.CarDto;
import com.example.car.entity.Car;
import com.example.car.entity.CarOwner;
import com.example.car.mapper.CarMapper;
import com.example.car.repo.CarOwnerRepository;
import com.example.car.repo.CarRepository;
import com.google.common.collect.Iterables;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
@Service
@AllArgsConstructor
@RequestMapping("/cars")
public class CarService {

    CarRepository carRepository;

    CarOwnerRepository carOwnerRepository;

    private final CarMapper converter = new CarMapper();

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public Response getCars() {
        List<Car> retrieveAllCars = carRepository.findAll();
        List<CarDto> carDtos = converter.convertToDtoList(retrieveAllCars);
        return Response.ok(carDtos).build();
    }

//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON_VALUE)
//    @Path("/{id}")
//    @ApiOperation(value = "Retrieve Car", response = CarDto.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public Response getCarById(@PathVariable(value = "id") String id) {
        Optional<Car> car = carRepository.findById(UUID.fromString(id));
        return car.isPresent()
                ? Response.ok(converter.convertToDto(car.get()))
                .build()
                : Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public Response saveStudent(CarDto car) {
        car.setId(null);
        Car carEntity = converter.convertToEntity(car);
        Car carSaved = carRepository.save(carEntity);
        return Response.status(Response
                        .Status
                        .CREATED
                        .getStatusCode())
                .entity(converter.convertToDto(carSaved))
                .build();
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/{id}",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public Response updateStudent(CarDto car) {
        Optional<Car> carEntity = carRepository.findById(car.getId());
        if (carEntity.isPresent()) {
            Car toBeSaved = converter.convertToEntity(car);
            List<CarOwner> newPreviousCarOwners = carEntity.get().getPreviousOwners();
            if (!carEntity.get().getPreviousOwners().get(0).getName().equals(car.getCurrentOwner())) {
                newPreviousCarOwners.add(carOwnerRepository.findByName(car.getCurrentOwner()));
            }
            toBeSaved.setPreviousOwners(newPreviousCarOwners);
            Car carUpdated = carRepository.save(toBeSaved);
            return Response.ok(converter.convertToDto(carUpdated))
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{id}",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public Response deleteStudent(@PathParam(value = "id") String id) {
        Optional<Car> studentR = carRepository.findById(UUID.fromString(id));

        if (studentR.isPresent()) {
            carRepository.deleteById(UUID.fromString(id));
            String message = "{\"result\": \"Car deleted\"}";
            return Response.ok().entity(message)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }
}
