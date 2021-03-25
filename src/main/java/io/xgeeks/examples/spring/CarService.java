package io.xgeeks.examples.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CarService {

    private final CarDAO dao;

    private final CarMapper mapper;

    @Autowired
    public CarService(CarDAO dao, CarMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public List<CarDTO> findAll(Page page) {
        Stream<Car> stream = dao.findAll(page);
        return stream.map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CarDTO> findById(Long id) {
        return dao.findBy(id).map(mapper::toDTO);
    }

    public CarDTO insert(CarDTO dto) {
        Car car = mapper.toEntity(dto);
        return mapper.toDTO(dao.insert(car));
    }

    public CarDTO update(Long id, CarDTO dto) {
        Car car = dao.findBy(id)
                .orElseThrow(() -> new EntityNotFoundException("Car does not find with the id " + id));
        car.update(mapper.toEntity(dto));
        dao.update(car);
        return mapper.toDTO(car);
    }

    public void delete(Long id) {
        dao.delete(id);
    }
}
