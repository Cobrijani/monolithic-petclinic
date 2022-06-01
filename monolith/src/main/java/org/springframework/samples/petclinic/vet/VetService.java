package org.springframework.samples.petclinic.vet;

import org.springframework.samples.petclinic.vet.dto.VetDto;

import java.util.Collection;

/**
 * Service for managing Vet related functionality.
 */
public interface VetService {

    /**
     * Retrieve all vets.
     *
     * @return collection of vets
     */
    Collection<VetDto> allVets();
}
