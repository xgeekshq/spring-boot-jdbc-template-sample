package io.xgeeks.examples.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CarDAOTest {

    @Autowired
    private CarDAO carDAO;

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
}