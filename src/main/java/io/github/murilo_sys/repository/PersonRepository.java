package io.github.murilo_sys.repository;

import io.github.murilo_sys.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
