package com.fiap.agenda.application.usecase;

import org.springframework.stereotype.Component;

import com.fiap.agenda.application.controller.dto.AgendaResponseDTO;
import com.fiap.agenda.application.controller.dto.CadastrarAgendaDTO;
import com.fiap.agenda.conf.AuthenticationFacade;
import com.fiap.agenda.domain.client.IBuscarMedicoAppClient;
import com.fiap.agenda.domain.gateway.IAgendaGateway;
import com.fiap.agenda.domain.usecase.ICadastrarAgenda;
import com.fiap.agenda.infra.persistence.mapper.AgendaMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CadastrarAgenda implements ICadastrarAgenda {

    private final IAgendaGateway agendaGateway;

    private final IBuscarMedicoAppClient buscarMedicoAppClient;

    private final AuthenticationFacade authenticationFacade;

    private final AgendaMapper agendaMapper;

    @Override
    public AgendaResponseDTO executar(CadastrarAgendaDTO cadastrarAgendaDTO, String bearerToken) {
        var agenda = agendaMapper.toAgenda(cadastrarAgendaDTO);

        var crm = authenticationFacade.getAuthentication().getName();
        var medicoResponse = buscarMedicoAppClient.buscarMedicoByCrm(Integer.parseInt(crm), bearerToken);

        agenda.setIdMedico(medicoResponse.getId()); 
        agenda = agendaGateway.salvar(agenda); 

        return agendaMapper.toAgendaResponseDTO(agenda);
    }

}
