package io.xgeeks.examples.spring;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toEntity(CarDTO dto);

    CarDTO toDTO(Car car);
}
