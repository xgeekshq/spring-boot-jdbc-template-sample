package io.xgeeks.examples.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CarDAOTest {

    @Autowired
    private CarDAO carDAO;

    @Autowired
    private JdbcTemplate template;

    @Test
    public void shouldFindById() {
        Assertions.assertNotNull(carDAO);
        Optional<Car> car = carDAO.findBy(1L);
        Assertions.assertNotNull(car);
    }

    @Test
    public void shouldInsertCar() {
        Car car = Car.builder()
                .withCity("Salvador")
                .withColor("Red")
                .withName("Fiat")
                .withModel("Model")
                .build();
        Car insert = carDAO.insert(car);
        Assertions.assertNotNull(insert);
        Assertions.assertNotNull(insert.getId());
    }

    @Test
    public void shouldDelete() {
        Car car = Car.builder()
                .withCity("Salvador")
                .withColor("Red")
                .withName("Fiat")
                .withModel("Model")
                .build();
        Car insert = carDAO.insert(car);
        carDAO.delete(insert.getId());
        Optional<Car> empty = carDAO.findBy(insert.getId());
        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    public void shouldUpdate() {
        Car car = Car.builder()
                .withCity("Salvador")
                .withColor("Red")
                .withName("Fiat")
                .withModel("Model")
                .build();
        Car insert = carDAO.insert(car);
        insert.setModel("Update");
        carDAO.update(insert);
    }

    @Test
    public void shouldFindAll() {
        template.execute("DELETE FROM CAR");
        List<Car> cars = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            Car car = Car.builder()
                    .withCity("Salvador")
                    .withColor("Red")
                    .withName("Fiat " + index)
                    .withModel("Model")
                    .build();
            cars.add(carDAO.insert(car));
        }
        Page page = Page.of(1, 2);
        List<Car> result = carDAO.findAll(page).collect(Collectors.toList());
        Assertions.assertEquals(2, result.size());
    }
}