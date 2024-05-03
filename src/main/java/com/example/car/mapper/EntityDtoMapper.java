package com.example.car.mapper;

import java.util.List;

public interface EntityDtoMapper<T, R> {


    public abstract T convertToEntity(R r);

    public abstract R convertToDto(T t);

    public abstract List<R> convertToDtoList(List<T> list);

}
