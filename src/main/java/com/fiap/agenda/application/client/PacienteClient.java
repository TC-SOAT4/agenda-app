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

import com.fiap.agenda.domain.client.IPacienteClient;
import com.fiap.agenda.domain.client.dto.PacienteResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PacienteClient implements IPacienteClient {

    private final RestTemplate restTemplate;

    @Value("${uri.api.paciente}")
    private String uri;

    @Override
    public PacienteResponse buscarPacienteByCpf(String cpf, String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", bearerToken);

        var uriBuscaMedico = uri + "/api/pacientes?cpf=" + cpf;

        var response = restTemplate.exchange(uriBuscaMedico, HttpMethod.GET, new HttpEntity<String>(headers),
                new ParameterizedTypeReference<List<PacienteResponse>>() {
                });
        var list = response != null && response.getBody() != null ? response.getBody() : null;
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }

}
