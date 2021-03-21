package io.xgeeks.examples.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerTest {

    @Autowired
    private TestRestTemplate template;

    @LocalServerPort
    private int port;

    @Test
    public void shouldInsert() {

    }

    public void shouldFindById() {}
    public void shouldReturnNotFound(){}
    public void shouldUpdate() {}
    public void shouldRemove() {}
    public void shouldFindAll(){}


}