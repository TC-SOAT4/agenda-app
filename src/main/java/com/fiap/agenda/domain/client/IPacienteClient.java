package com.fiap.agenda.domain.client;

import com.fiap.agenda.domain.client.dto.PacienteResponse;

public interface IPacienteClient {

    public PacienteResponse buscarPacienteByCpf(String cpf, String bearerToken);

}
