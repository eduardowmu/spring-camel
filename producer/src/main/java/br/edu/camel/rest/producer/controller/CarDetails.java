package br.edu.camel.rest.producer.controller;

import br.edu.camel.rest.producer.bean.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/car")
public class CarDetails {
    @GetMapping("/")
    public Car getDetails() {
        return new Car("Swift",
                        "ZDI",
                    "Suzuki");
    }
}