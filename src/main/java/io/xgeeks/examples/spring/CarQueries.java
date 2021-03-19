package io.xgeeks.examples.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CarQueries {

    @Value("${car.query.find.by.id}")
    private String findById;
    @Value("${car.query.delete.by.id}")
    private String deleteById;
    @Value("${car.query.update}")
    private String update;

    public String getFindById() {
        return findById;
    }

    public String getDeleteById() {
        return deleteById;
    }

    public String getUpdate() {
        return update;
    }

}
