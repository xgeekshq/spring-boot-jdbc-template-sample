package io.xgeeks.examples.spring;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class CarDAO {

    private final JdbcTemplate template;

    public CarDAO(JdbcTemplate template) {
        this.template = template;
        this.r
    }

    public void insert(Car car) {

    }

    public Optional<Car> findBy(Long id) {
        return Optional.empty();
    }

    public void delete(Long id) {

    }

    public void update(Car car) {

    }
}
