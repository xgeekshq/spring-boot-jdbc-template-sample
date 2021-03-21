package io.xgeeks.examples.spring;

public class CarBuilder {
    private Long id;
    private String name;
    private String city;
    private String model;
    private String color;

    public CarBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CarBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CarBuilder city(String city) {
        this.city = city;
        return this;
    }

    public CarBuilder model(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder color(String color) {
        this.color = color;
        return this;
    }

    public Car build() {
        return new Car(id, name, city, model, color);
    }
}