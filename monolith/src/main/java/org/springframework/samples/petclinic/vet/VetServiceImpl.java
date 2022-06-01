package org.springframework.samples.petclinic.vet;

import org.springframework.samples.petclinic.vet.model.Vet;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
class VetServiceImpl implements VetService {

    private final VetRepository repository;

    public VetServiceImpl(VetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Vet> allVets() {
        return repository.findAll();
    }
}
