package com.fiap.agenda.domain.client;

import com.fiap.agenda.domain.client.dto.MedicoResponse;

public interface IBuscarMedicoAppClient {

    public MedicoResponse buscarMedicoByCrm(Integer crm, String bearerToken);

}
