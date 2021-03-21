package io.xgeeks.examples.spring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

public class CarDTO {

    private Long id;

    private String name;

    private String city;

    private String model;

    private String color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarDTO carDTO = (CarDTO) o;
        return Objects.equals(id, carDTO.id)
                && Objects.equals(name, carDTO.name)
                && Objects.equals(city, carDTO.city)
                && Objects.equals(model, carDTO.model)
                && Objects.equals(color, carDTO.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, model, color);
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
