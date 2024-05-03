package com.example.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @JsonProperty("id")
    @Column(name = "id", unique = true, updatable = false, insertable = false, nullable = false, columnDefinition = "uuid")
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CarOwner> previousOwners;
}