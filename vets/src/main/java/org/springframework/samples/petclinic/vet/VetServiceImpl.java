package org.springframework.samples.petclinic.vet;

import org.springframework.samples.petclinic.vet.dto.SpecialtyDto;
import org.springframework.samples.petclinic.vet.dto.VetDto;
import org.springframework.samples.petclinic.vet.model.Specialty;
import org.springframework.samples.petclinic.vet.model.Vet;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
class VetServiceImpl implements VetService {

    private final VetRepository repository;

    public VetServiceImpl(VetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<VetDto> allVets() {
        return repository.findAll().stream().map(this::vetToVetDto).collect(Collectors.toList());
    }

    private VetDto vetToVetDto(Vet vet){
        VetDto vetDto = new VetDto();
        vetDto.setId(vet.getId());
        vetDto.setFirstName(vet.getFirstName());
        vetDto.setLastName(vet.getLastName());
        vetDto.setSpecialties(
                vet.getSpecialties().stream()
               .map(this::specialtyToSpecialtyDto).collect(Collectors.toList())
        );
        vetDto.setNrOfSpecialties(vet.getNrOfSpecialties());
        return vetDto;
    }

    private SpecialtyDto specialtyToSpecialtyDto(Specialty specialty) {
        SpecialtyDto specialtyDto = new SpecialtyDto();
        specialtyDto.setId(specialty.getId());
        specialtyDto.setName(specialty.getName());
        return specialtyDto;
    }
}
