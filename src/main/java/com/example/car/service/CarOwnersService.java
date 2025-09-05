package com.example.car.service;

import com.example.car.dto.CarOwnerDto;
import com.example.car.entity.Car;
import com.example.car.entity.CarOwner;
import com.example.car.mapper.CarMapper;
import com.example.car.mapper.CarOwnerMapper;
import com.example.car.repo.CarOwnerRepository;
import com.example.car.repo.CarRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
@Path("/carowners")
@Api(value = "Car owners")
public class CarOwnersService {

    CarOwnerRepository carOwnerRepo;

    CarRepository carRepository;

    private final CarOwnerMapper carownerConverter = new CarOwnerMapper();
    private final CarMapper carConverter = new CarMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Path("/{carId}")
    @ApiOperation(value = "Retrieve (previous) owners of a car", response = CarOwnerDto[].class)
    public Response getCarOwnersById(@PathParam(value = "carId") String id) {
        List<CarOwnerDto> carOwners = getCarOwnerDtoList(id);
        return Response.ok(carOwners).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Path("/{carId}")
    @ApiOperation(value = "Save grade of a subject of a student")
    public Response saveCarOwnerByCarId(@PathParam(value = "carId") String id, CarOwnerDto carOwner) {
        Optional<Car> car = getCar(id);
        if (car.isPresent()) {
            CarOwner carOwnerEntity = carownerConverter.convertToEntity(carOwner);
            carOwnerEntity.setCar(car.get());
            carOwnerRepo.save(carOwnerEntity);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }

    private Optional<Car> getCar(String id) {
        return carRepository.findById(UUID.fromString(id));
    }

    private List<CarOwnerDto> getCarOwnerDtoList(String id) {
        return carownerConverter.convertToDtoList(carOwnerRepo.findByCarId(UUID.fromString(id)));
    }
}
