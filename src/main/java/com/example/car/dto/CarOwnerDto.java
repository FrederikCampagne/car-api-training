package com.example.car.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarOwnerDto {

    @JsonProperty("id")
    private UUID id;

    @NonNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("dateStoppedBeingOwner")
    private String dateStoppedBeingOwner;

}
