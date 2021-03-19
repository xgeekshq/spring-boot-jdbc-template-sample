package io.xgeeks.examples.spring;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Car {

    private Long id;

    private String name;

    private String city;

    private String model;

    private String color;

    Car(Long id, String name,
        String city, String model,
        String color) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.model = model;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", this.id);
        parameters.put("name", this.name);
        parameters.put("city", this.city);
        parameters.put("model", this.model);
        parameters.put("color", this.color);
        return parameters;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public static CarBuilder builder() {
        return new CarBuilder();
    }
}
