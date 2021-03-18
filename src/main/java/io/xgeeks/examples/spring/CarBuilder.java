package io.xgeeks.examples.spring;

public class CarBuilder {
    private Long id;
    private String name;
    private String city;
    private String model;
    private String color;

    public CarBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CarBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CarBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public CarBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public Car build() {
        return new Car(id, name, city, model, color);
    }
}