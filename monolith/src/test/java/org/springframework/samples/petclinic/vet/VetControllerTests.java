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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
class VetControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testShowVetListHtml() throws Exception {
        mockMvc.perform(get("/vets"))
            .andExpect(status().isOk())
            .andExpect(xpath("//table[@id='vets']").exists())
            .andExpect(xpath("//table[@id='vets']/tbody/tr").nodeCount(6))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=1]/td[position()=1]").string("James Carter"))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=1]/td[position()=2]/span").string("none"))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=2]/td[position()=1]").string("Helen Leary"))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=2]/td[position()=2]/span").string("radiology "))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=3]/td[position()=1]").string("Linda Douglas"))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=3]/td[position()=2]/span").nodeCount(2))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=3]/td[position()=2]/span[position()=1]").string("dentistry "))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=3]/td[position()=2]/span[position()=2]").string("surgery "))
        ;
    }

}
