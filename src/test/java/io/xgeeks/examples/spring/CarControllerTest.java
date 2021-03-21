package io.xgeeks.examples.spring;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerTest {

    @Autowired
    private TestRestTemplate template;

    @LocalServerPort
    private int port;

    @Test
    public void shouldInsert() {
        CarDTO carDTO = getCarDTO();
        CarDTO dto = this.template.postForObject(getUrl(), carDTO, CarDTO.class);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertEquals(carDTO.getCity(), dto.getCity());
        Assertions.assertEquals(carDTO.getColor(), dto.getColor());
        Assertions.assertEquals(carDTO.getModel(), dto.getModel());
        Assertions.assertEquals(carDTO.getName(), dto.getName());
    }

    @Test
    public void shouldFindById() {
        CarDTO carDTO = this.template.postForObject(getUrl(), getCarDTO(), CarDTO.class);
        CarDTO dto = this.template.getForObject(getUrl() + carDTO.getId(), CarDTO.class);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertEquals(carDTO.getCity(), dto.getCity());
        Assertions.assertEquals(carDTO.getColor(), dto.getColor());
        Assertions.assertEquals(carDTO.getModel(), dto.getModel());
        Assertions.assertEquals(carDTO.getName(), dto.getName());
    }

    @Test
    public void shouldReturnNotFound() {
        ResponseEntity<CarDTO> responseEntity = this.template.getForEntity(getUrl() + Long.MAX_VALUE,
                CarDTO.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    public void shouldUpdate() {
        CarDTO dto = this.template.postForObject(getUrl(), getCarDTO(), CarDTO.class);
        dto.setName("Update");
        ResponseEntity<CarDTO> entity = this.template.exchange(getUrl() + dto.getId(),
                HttpMethod.PUT,
                new HttpEntity<>(dto), CarDTO.class);

        CarDTO response = entity.getBody();

        Assertions.assertEquals(dto.getCity(), response.getCity());
        Assertions.assertEquals(dto.getColor(), response.getColor());
        Assertions.assertEquals(dto.getModel(), response.getModel());
        Assertions.assertEquals(dto.getName(), response.getName());

    }

    @Test
    public void shouldRemove() {
        CarDTO carDTO = this.template.postForObject(getUrl(), getCarDTO(), CarDTO.class);
        ResponseEntity<CarDTO> response = this.template.getForEntity(getUrl() + carDTO.getId(),
                CarDTO.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        this.template.delete(getUrl() + carDTO.getId());
        ResponseEntity<CarDTO> responseB = this.template.getForEntity(getUrl() + carDTO.getId(),
                CarDTO.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), responseB.getStatusCodeValue());
    }

    @Test
    public void shouldFindAllByDefaultValues() {
        for (int index = 0; index < 20; index++) {
            this.template.postForObject(getUrl(), getCarDTO(), CarDTO.class);
        }
        ResponseEntity<List<CarDTO>> response = this.template.exchange(getUrl(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CarDTO>>() {
                });

        List<CarDTO> body = response.getBody();
        Assertions.assertEquals(10, body.size());
    }

    @Test
    public void shouldFindAllOverWriteValues() {
        for (int index = 0; index < 20; index++) {
            this.template.postForObject(getUrl(), getCarDTO(), CarDTO.class);
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getUrl())
                .queryParam("page", 2)
                .queryParam("size", 5);

        ResponseEntity<CarDTO[]> response = this.template.exchange(builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<CarDTO[]>() {
                });

        CarDTO[] body = response.getBody();
        Assertions.assertEquals(5, body.length);

    }

    private CarDTO getCarDTO() {
        CarDTO carDTO = new CarDTO();
        carDTO.setCity("Salvador");
        carDTO.setColor("Blue");
        carDTO.setModel("Model");
        carDTO.setName("Car");
        return carDTO;
    }

    private String getUrl() {
        return "http://localhost:" + port + "/cars/";
    }

}