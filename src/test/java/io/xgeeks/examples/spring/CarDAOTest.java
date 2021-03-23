package io.xgeeks.examples.spring;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
                .city("Salvador")
                .color("Red")
                .name("Fiat")
                .model("Model")
                .build();
        Car insert = carDAO.insert(car);
        Assertions.assertNotNull(insert);
        Assertions.assertNotNull(insert.getId());
    }

    @Test
    public void shouldDelete() {
        Car car = Car.builder()
                .city("Salvador")
                .color("Red")
                .name("Fiat")
                .model("Model")
                .build();
        Car insert = carDAO.insert(car);
        carDAO.delete(insert.getId());
        Optional<Car> empty = carDAO.findBy(insert.getId());
        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    public void shouldUpdate() {
        Car car = Car.builder()
                .city("Salvador")
                .color("Red")
                .name("Fiat")
                .model("Model")
                .build();
        Car insert = carDAO.insert(car);

        insert.update(Car.builder()
                .city("Salvador")
                .color("Red")
                .name("Fiat")
                .model("Update")
                .build());
        carDAO.update(insert);
    }

    @Test
    public void shouldFindAll() {
        template.execute("DELETE FROM CAR");
        List<Car> cars = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            Car car = Car.builder()
                    .city("Salvador")
                    .color("Red")
                    .name("Fiat " + index)
                    .model("Model")
                    .build();
            cars.add(carDAO.insert(car));
        }
        Page page = Page.of(1, 2);
        List<Car> result = carDAO.findAll(page).collect(Collectors.toList());
        Assertions.assertEquals(2, result.size());
        MatcherAssert.assertThat(result, Matchers.contains(cars.get(0), cars.get(1)));
        Page nextPage = page.next();
        result = carDAO.findAll(nextPage).collect(Collectors.toList());
        Assertions.assertEquals(2, result.size());
        MatcherAssert.assertThat(result, Matchers.contains(cars.get(2), cars.get(3)));
    }
}