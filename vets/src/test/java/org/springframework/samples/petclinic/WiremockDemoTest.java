package org.springframework.samples.petclinic;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

class WiremockDemoTest {

    private final WireMockServer wireMock = new WireMockServer(options().port(8089));

    @BeforeEach
    void startWireMock() {
        wireMock.start();
    }

    @AfterEach
    void stopWireMock() {
        wireMock.stop();
    }

    @Test
    void how_to_stub_a_server_with_wiremock() {
        wireMock.stubFor(get(urlEqualTo("/demo"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[\"James\", \"Helen\" ]")));

        String[] result = new RestTemplate().getForObject("http://localhost:8089/demo", String[].class);

        assertThat(result).containsExactly("James", "Helen");
    }
}