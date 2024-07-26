package com.fiap.agenda.application.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fiap.agenda.domain.client.IBuscarMedicoAppClient;
import com.fiap.agenda.domain.client.dto.MedicoResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BuscarMedicoAppClient implements IBuscarMedicoAppClient {

    private final RestTemplate restTemplate;

    @Value("${uri.api.buscar.medico}")
    private String uri;

    public MedicoResponse buscarMedicoByCrm(Integer crm, String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", bearerToken);

        var uriBuscaMedico = uri + "/api/medicos?crm=" + crm;

        var response = restTemplate.exchange(uriBuscaMedico, HttpMethod.GET, new HttpEntity<String>(headers),
                new ParameterizedTypeReference<List<MedicoResponse>>() {
                });
        var list = response != null && response.getBody() != null ? response.getBody() : null;
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}
