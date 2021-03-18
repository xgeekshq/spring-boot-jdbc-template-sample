package io.xgeeks.examples.spring;

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
