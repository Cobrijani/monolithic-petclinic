package org.springframework.samples.petclinic.vet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.vet.model.Vet;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RepositoryIntegrationTests {

    @Autowired
    VetRepository vetsRepository;

    @Test
    void testFindVets() {
        Collection<Vet> all = vetsRepository.findAll();
        assertThat(all).hasSize(6);
    }
}
