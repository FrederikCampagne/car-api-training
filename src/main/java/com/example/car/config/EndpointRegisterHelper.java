package com.example.car.config;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EndpointRegisterHelper {

    public List<Class<?>> getEndpoints(String packageNameEndpoints) {
        return getListOfAllClassesInPackage(packageNameEndpoints);
    }

    private List<Class<?>> getListOfAllClassesInPackage(String packageName) {
        return new ArrayList<>(new Reflections(packageName, new SubTypesScanner(false))
                .getSubTypesOf(Object.class));
    }
}
