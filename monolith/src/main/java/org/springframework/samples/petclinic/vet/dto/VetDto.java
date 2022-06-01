package org.springframework.samples.petclinic.vet.dto;

import java.util.List;
import java.util.Set;

public class VetDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<SpecialtyDto> specialties;
    private Integer nrOfSpecialties;

    public VetDto() {
    }

    public VetDto(Integer id, String firstName, String lastName, List<SpecialtyDto> specialties, Integer nrOfSpecialties) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialties = specialties;
        this.nrOfSpecialties = nrOfSpecialties;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SpecialtyDto> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<SpecialtyDto> specialties) {
        this.specialties = specialties;
    }

    public Integer getNrOfSpecialties() {
        return nrOfSpecialties;
    }

    public void setNrOfSpecialties(Integer nrOfSpecialties) {
        this.nrOfSpecialties = nrOfSpecialties;
    }
}
