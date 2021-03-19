package io.xgeeks.examples.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CarDAOTest {

    @Autowired
    private CarDAO carDAO;

    @Test
    public void shouldBeNotNull() {
        Assertions.assertNotNull(carDAO);
    }
}