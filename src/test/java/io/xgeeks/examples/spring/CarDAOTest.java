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
    public void shouldBeNotNull() {
        Assertions.assertNotNull(carDAO);
        Optional<Car> by = carDAO.findBy(1L);
        Assertions.assertNotNull(by);
    }
}