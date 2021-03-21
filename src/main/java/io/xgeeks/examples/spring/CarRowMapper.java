package io.xgeeks.examples.spring;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("ID");
        String name = resultSet.getString("name");
        String city = resultSet.getString("city");
        String model = resultSet.getString("model");
        String color = resultSet.getString("color");
        return Car.builder().id(id).name(name)
                .city(city)
                .model(model)
                .color(color).build();
    }
}
