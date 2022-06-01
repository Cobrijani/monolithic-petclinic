package org.springframework.samples.petclinic.vet;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.vet.dto.VetDto;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RemoteVetServiceImpl implements VetService{
    private int port;

    public RemoteVetServiceImpl() {
        port = 8081;
    }

    public RemoteVetServiceImpl(int port) {
        this.port = port;
    }

    @Override
    public Collection<VetDto> allVets() {
        RestTemplate template = new RestTemplate();
        try{
            ResponseEntity<ArrayList<VetDto>> response = template.exchange("http://localhost:"+ port + "/vets", HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<VetDto>>() {
            });
            return response.getBody();
        }catch(Exception ex) {
            return new ArrayList<>();
        }
    }
}
