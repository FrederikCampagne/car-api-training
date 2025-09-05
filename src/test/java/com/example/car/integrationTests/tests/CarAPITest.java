package com.example.car.integrationTests.tests;

import com.example.car.integrationTests.clients.CarClient;
import com.example.car.dto.CarDto;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class CarAPITest {

    private CarClient carClient;

    @Before
    public void setupClient() {
        carClient = new CarClient();
    }

    @Test
    public void endpoint_shouldReturnCarWithBrandToyota_whenGet() {

        List<CarDto> cars = carClient.getCars();

        Assert.assertEquals("Frederik Campagne", cars.get(0).getCurrentOwner());

        cars.stream().forEach(car -> {
            Assert.assertEquals("Toyota", car.getBrand());
        });

    }

    @Test
    public void endpoint_shouldReturnStatus200_whenGet() {

        Response carsReponse = carClient.getCarsReponse();

        carsReponse
                .then()
                .assertThat()
                .statusCode(200)
                .body("$[0].currentOwner", equalTo("Frederik Campagne"));

    }
}

