package com.tivit.cliente.service;

import com.tivit.cliente.VO.ClienteVO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger logger = Logger.getLogger(ClienteServiceImpl.class.getName());
    private final String URI = "http://localhost:8080";


    public List<ClienteVO> getClientes() {
        final String uri = URI + "/clientes";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ClienteVO>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ClienteVO>>() {
                });

        List<ClienteVO> clienteVOS = Optional.of(response).filter(r -> r.getStatusCode().equals(HttpStatus.OK))
                .map(HttpEntity::getBody).orElse(null);

        Optional.of(response).filter(r -> !r.getStatusCode().equals(HttpStatus.OK))
                .ifPresent(r -> logger.info(uri + " - " + r.getStatusCode().toString()));

        return clienteVOS;
    }
}
