package io.github.murilo_sys.services;

import io.github.murilo_sys.data.dto.PersonDTO;
import io.github.murilo_sys.exception.ResourceNotFoundExcepiton;
import io.github.murilo_sys.model.Person;
import io.github.murilo_sys.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.github.murilo_sys.mapper.ObjectMapper.*;

@Service
public class PersonServices {


    final PersonRepository repository;
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    public PersonServices(PersonRepository repository) {
        this.repository = repository;
    }


    public List<PersonDTO> findAll() {
        logger.info("Finding all People!");

        return parseListObjects(repository.findAll(), PersonDTO.class);

    }


    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundExcepiton("No records found for this id"));

        return parseObject(entity, PersonDTO.class);
    }


    public PersonDTO create(PersonDTO person) {

        if (person.getId() != null) {
            logger.info("Não é permitido setar ID ao person. Setando automaticamente!");
            person.setId(null);
        }

        //converte de DTO para entidade
        var entity = parseObject(person, Person.class);

        var savedEntity = repository.save(entity);

        logger.info("Creating one Person! ID: " + savedEntity.getId());

        return parseObject(savedEntity, PersonDTO.class);
    }


    public void delete(Long id) {
        logger.info("Deleting id: " + id);

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundExcepiton("No records found for this id"));

        repository.deleteById(entity.getId());
    }


    public PersonDTO update(PersonDTO person) {
        logger.info("Update one Person!");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundExcepiton("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }
}
