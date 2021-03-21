package io.xgeeks.examples.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public List<CarDTO> findAll(@RequestParam(value = "page", defaultValue = "1") Long page,
                                @RequestParam(value = "page", defaultValue = "10") Long size) {
        return service.findAll(Page.of(page, size));
    }

    @GetMapping("{id}")
    public CarDTO findById(@PathVariable Long id) {
        Optional<CarDTO> car = service.finById(id);
        return car.orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                "Unable to find the car " + id));
    }

    @PostMapping
    public CarDTO insert(@RequestBody CarDTO dto) {
        return service.insert(dto);
    }

    @PutMapping(value = "{id}")
    public CarDTO update(@PathVariable("id") Long id, @RequestBody CarDTO dto) {
        return service.update(id, dto);
    }
}
