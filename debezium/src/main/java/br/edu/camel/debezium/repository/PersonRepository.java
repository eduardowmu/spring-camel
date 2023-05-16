package br.edu.camel.debezium.repository;

import br.edu.camel.debezium.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
