package br.com.erudio.mapper.custom;

import br.com.erudio.data.dto.v2.PersonDTOV2;
import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person){
        PersonDTOV2 dto = new PersonDTOV2();
        dto.setId(person.getId());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setBirthday(new Date());
        return dto;
    }

    public Person convertDTOToEntity(PersonDTOV2 personDTOV2){
        Person person = new Person();
        person.setAddress(personDTOV2.getAddress());
        person.setGender(personDTOV2.getGender());
        person.setFirstName(personDTOV2.getFirstName());
        person.setLastName(personDTOV2.getLastName());
        //person.setBirthday(new Date());
        return person;
    }
}
