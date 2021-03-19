package io.xgeeks.examples.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class CarDAO {

    private final NamedParameterJdbcTemplate template;
    private final CarQueries queries;
    private final RowMapper<Car> rowMapper;
    private final SimpleJdbcInsert insert;

    @Autowired
    public CarDAO(NamedParameterJdbcTemplate template, CarQueries queries) {
        this.template = template;
        this.rowMapper = new BeanPropertyRowMapper<>(Car.class);
        this.queries = queries;
        this.insert = new SimpleJdbcInsert(template.getJdbcTemplate());
        insert.setTableName("CAR");
        insert.usingGeneratedKeyColumns("ID");
    }

    @Transactional
    public Car insert(Car car) {
        Number id = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(car));
        return findBy(id.longValue()).orElseThrow(() -> new IllegalStateException(""));
    }

    public Optional<Car> findBy(Long id) {
        String sql = queries.getFindById();
        Map<String, Object> parameters = Collections.singletonMap("id", id);
        return template.queryForStream(sql, parameters, rowMapper).findFirst();
    }

    @Transactional
    public boolean delete(Long id) {
        String sql = queries.getDeleteById();
        Map<String, Object> paramMap = Collections.singletonMap("id", id);
        return template.update(sql, paramMap) == 1;
    }

    @Transactional
    public boolean update(Car car) {
        String sql = queries.getUpdate();
        Map<String, Object> paramMap = car.toMap();
        return template.update(sql, paramMap) == 1;
    }

    public Stream<Car> findAll(Page page) {
        String sql = queries.getFindAll();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("limit", page.getLimit());
        paramMap.put("offset", page.getOffset());
        return template.queryForStream(sql, paramMap, rowMapper);
    }
}
