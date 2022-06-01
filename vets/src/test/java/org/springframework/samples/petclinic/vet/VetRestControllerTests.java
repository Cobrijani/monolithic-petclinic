/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VetRestControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testShowVetListJson() throws Exception {
        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].firstName").value("James"))
                .andExpect(jsonPath("$.[0].lastName").value("Carter"))
                .andExpect(jsonPath("$.[0].specialties").isEmpty())
                .andExpect(jsonPath("$.[0].nrOfSpecialties").value(0))
                .andExpect(jsonPath("$.[1].firstName").value("Helen"))
                .andExpect(jsonPath("$.[1].lastName").value("Leary"))
                .andExpect(jsonPath("$.[1].specialties.[0].name").value("radiology"))
                .andExpect(jsonPath("$.[2].firstName").value("Linda"))
                .andExpect(jsonPath("$.[2].lastName").value("Douglas"))
                .andExpect(jsonPath("$.[2].nrOfSpecialties").value(2))
                .andExpect(jsonPath("$.[2].specialties.[0].name").value("dentistry"))
                .andExpect(jsonPath("$.[2].specialties.[1].name").value("surgery"));
    }

}
