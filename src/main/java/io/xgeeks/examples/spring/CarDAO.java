package io.xgeeks.examples.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class CarDAO {

    private final JdbcTemplate template;
    private final RowMapper<Car> rowMapper;
    private final SimpleJdbcInsert insert;

    @Autowired
    public CarDAO(JdbcTemplate template) {
        this.template = template;
        this.rowMapper = new BeanPropertyRowMapper<>(Car.class);
        this.insert = new SimpleJdbcInsert(template);
    }

    public Car insert(Car car) {
        Number id = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(car));
        return findBy(id.longValue()).orElseThrow(() -> new IllegalStateException(""));
    }

    public Optional<Car> findBy(Long id) {
        String sql = "SELECT * FROM CAR WHERE id =:id";
        Map<String, Object> paramMap = Collections.singletonMap("id", id);
        return template.queryForStream(sql, rowMapper, paramMap).findFirst();
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM CAR WHERE id =:id?";
        Map<String, Object> paramMap = Collections.singletonMap("id", id);
        return template.update(sql, paramMap) == 1;
    }

    public boolean update(Car car) {
        String sql = "update CAR set name = :name, city = :city, model= :model, color =:color  where id = :id";
        Map<String, Object> paramMap = car.toMap();
        return template.update(sql, paramMap) == 1;
    }
}
