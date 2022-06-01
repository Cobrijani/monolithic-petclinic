package org.springframework.samples.petclinic.vet;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.vet.dto.VetDto;

import java.util.Collection;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VetClientTest {

    private final WireMockServer wireMock = new WireMockServer(options().port(8082));
    private VetClient remoteVetService = new VetClient(8082);

    @BeforeEach
    void startWireMock() {
        wireMock.start();
    }

    @AfterEach
    void stopWireMock() {
        wireMock.stop();
    }

    @Test
    void getAllVetsSuccess() {
        wireMock.stubFor(get(urlEqualTo("/vets"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\":1,\"firstName\":\"James\",\"lastName\":\"Carter\",\"specialties\":[]," +
                                "\"nrOfSpecialties\":0},{\"id\":2,\"firstName\":\"Helen\",\"lastName\":\"Leary\"," +
                                "\"specialties\":[{\"id\":1,\"name\":\"radiology\"}],\"nrOfSpecialties\":1},{\"id\":3," +
                                "\"firstName\":\"Linda\",\"lastName\":\"Douglas\",\"specialties\":[{\"id\":3,\"name\":" +
                                "\"dentistry\"},{\"id\":2,\"name\":\"surgery\"}],\"nrOfSpecialties\":2},{\"id\":4,\"firstName\"" +
                                ":\"Rafael\",\"lastName\":\"Ortega\",\"specialties\":[{\"id\":2,\"name\":\"surgery\"}]," +
                                "\"nrOfSpecialties\":1},{\"id\":5,\"firstName\":\"Henry\",\"lastName\":\"Stevens\",\"specialties\"" +
                                ":[{\"id\":1,\"name\":\"radiology\"}],\"nrOfSpecialties\":1},{\"id\":6,\"firstName\":\"Sharon\"," +
                                "\"lastName\":\"Jenkins\",\"specialties\":[],\"nrOfSpecialties\":0}]")));

        Collection<VetDto> vets = remoteVetService.allVets();
        assertThat(vets).hasSize(6);
    }

    @Test
    void getAllVetsError() {
        wireMock.stubFor(get(urlEqualTo("/vets"))
                .willReturn(aResponse().withStatus(500)));

        Collection<VetDto> vets = remoteVetService.allVets();
        assertThat(vets).hasSize(0);
    }
}
