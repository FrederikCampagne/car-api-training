package com.example.car.config;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;


@Configuration
@ApplicationPath("/api/car-registration/v1")
public class AppResourceConfig extends ResourceConfig {

    private static final String SERVICE_RESOURCE_PACKAGE = "com.example.car.service";
    EndpointRegisterHelper helper = new EndpointRegisterHelper();

    public AppResourceConfig() {
        helper.getEndpoints(SERVICE_RESOURCE_PACKAGE)
                .forEach(this::register);
    }

    @PostConstruct
    public void init() {
        this.configureSwagger();
    }

    private void configureSwagger() {
//        this.register(ApiListingResource.class);
//        this.register(SwaggerSerializers.class);
//
//        BeanConfig config = new BeanConfig();
//        config.setConfigId("springboot/jersey/swagger");
//        config.setTitle("Spring Boot + Jersey + Swagger Example");
//        config.setVersion("v1");
//        config.setContact("F. Campagne");
//        config.setSchemes(new String[]{"http", "https"});
//        config.setBasePath("/api/car-registration/v1");
//        config.setResourcePackage(SERVICE_RESOURCE_PACKAGE);
//        config.setPrettyPrint(true);
//        config.setScan(false);
    }
}
