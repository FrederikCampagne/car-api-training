package com.example.car.integrationTests.mockIntegrationTests;

import com.example.car.CarRegistrationApplication;
import com.example.car.config.AppResourceConfig;
import com.example.car.entity.Car;
import com.example.car.repo.CarRepository;
import com.example.car.service.CarService;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {CarRegistrationApplication.class, CarService.class, AppResourceConfig.class})
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class MockIntegrationTest {

    @Autowired
    private CarRepository carRepo;

    @Test
    public void carService_ShouldReturnPersistedCars_whenGetAllCars() throws Exception {
        Car car = Car.builder()
                .id(null)
                .brand("Toyota")
                .currentOwner("Frederik Campagne")
                .licensePlate("RV192K")
                .firstRegistration("1-12-2016").build();

        carRepo.save(car);


        RestAssured.get("http://localhost:8888/api/car-registration/v1/cars")
                .then()
                .body("[0].brand", Matchers.equalTo("Toyota"));

    }
}
