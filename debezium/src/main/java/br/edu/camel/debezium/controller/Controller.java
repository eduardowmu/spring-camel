package br.edu.camel.debezium.controller;

import br.edu.camel.debezium.model.Person;
import br.edu.camel.debezium.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class Controller {
    private final PersonService personService;

    @Autowired
    public Controller(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> listAll() {
        return ResponseEntity.ok(this.personService.listAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return ResponseEntity.ok(this.personService.save(person));
    }
}
