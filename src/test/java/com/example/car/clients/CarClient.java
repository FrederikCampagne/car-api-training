package com.example.car.clients;

import com.example.car.dto.CarDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarClient {
    private RequestSpecification reqSpec;

    public CarClient() {
        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8888")
                .setBasePath("/api/car-registration/v1/cars")
                .build();
    }

    public List<CarDto> getCars() {
        return Arrays.stream(RestAssured
                .given(this.reqSpec)
                .get()
                .as(CarDto[].class)).collect(Collectors.toList());
    }

    public Response getCarsReponse() {
        return RestAssured
                .given(this.reqSpec)
                .get()
                .thenReturn();
    }
}
