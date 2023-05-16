package br.edu.camel.debezium.service;

import br.edu.camel.debezium.model.Person;
import br.edu.camel.debezium.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        return this.personRepository.save(person);
    }

    public List<Person> listAll() {
        return this.personRepository.findAll();
    }

    public HttpStatus delete(Long id) {
        var person = this.personRepository.findById(id);
        if(person.isPresent()) {
            this.personRepository.delete(person.get());
            return HttpStatus.OK;
        }
        return HttpStatus.EXPECTATION_FAILED;
    }
}
