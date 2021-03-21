package io.xgeeks.examples.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerTest {

    @Autowired
    private TestRestTemplate template;

    @LocalServerPort
    private int port;

    @Test
    public void shouldInsert() {
        CarDTO carDTO = new CarDTO();
        carDTO.setCity("Salvador");
        carDTO.setColor("Blue");
        carDTO.setModel("Model");
        carDTO.setName("Car");
        CarDTO dto = this.template.postForObject("http://localhost:" + port + "/cars", carDTO, CarDTO.class);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertEquals(carDTO.getCity(), dto.getCity());
        Assertions.assertEquals(carDTO.getColor(), dto.getColor());
        Assertions.assertEquals(carDTO.getModel(), dto.getModel());
        Assertions.assertEquals(carDTO.getName(), dto.getName());
    }

    public void shouldFindById() {}
    public void shouldReturnNotFound(){}
    public void shouldUpdate() {}
    public void shouldRemove() {}
    public void shouldFindAll(){}


}