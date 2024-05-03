package com.example.car.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDto {

    @JsonProperty("id")
    private UUID id;

    @NonNull
    @JsonProperty("brand")
    private String brand;

    @NonNull
    @JsonProperty("licensePlate")
    private String licensePlate;

    @NonNull
    @JsonProperty("firstRegistration")
    private String firstRegistration;

    @NonNull
    @JsonProperty("currentOwner")
    private String currentOwner;

    @NonNull
    @JsonProperty("previousOwners")
    private List<CarOwnerDto> previousOwners;
}
