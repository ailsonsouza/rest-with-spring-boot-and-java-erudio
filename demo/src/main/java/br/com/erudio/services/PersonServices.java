package br.com.erudio.services;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.dto.v1.PersonDTO;
import br.com.erudio.data.dto.v2.PersonDTOV2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.concurrent.atomic.AtomicLong;

import static br.com.erudio.mapper.ObjectMapper.parseListObject;
import static br.com.erudio.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PersonServices.class);
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;


    public List<PersonDTO> findAllPeople(){
        logger.info("finding all people");

        var allPeople = parseListObject(personRepository.findAll(), PersonDTO.class);
        allPeople.forEach(this::addHateoasLinks);
        return allPeople;
    }

    public PersonDTO findById(Long id){
        logger.info("finding one Person");
        var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO createPerson(PersonDTO person){
        logger.info("creating person");
        var entity = parseObject(person, Person.class);
        var creatingEntity = parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(creatingEntity);
        return creatingEntity;
    }

    public PersonDTOV2 createPersonV2(PersonDTOV2 person){
        logger.info("creating person v2");
        var entity = personMapper.convertDTOToEntity(person);
        logger.info("ID after save: ");
        return personMapper.convertEntityToDTO(personRepository.save(entity));
    }

    public PersonDTO updatePerson(PersonDTO person){
        logger.info("updating personal datas");
        Person personToUpdate = personRepository.findById(person.getId())
                .orElseThrow(() -> new RuntimeException("Person not found"));
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setGender(person.getGender());
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());

        return parseObject(personRepository.save(personToUpdate), PersonDTO.class);

    }

    public void deletePerson(Long id){
        logger.info("deleting person");
        Person personToDelete = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        personRepository.delete(personToDelete);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).deletePersonById(dto.getId())).withRel("deleteById").withType("DELETE"));
        dto.add(linkTo(methodOn(PersonController.class).creatPerson(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).updatePerson(dto)).withRel("update").withType("PUT"));
    }
}
