package com.fiap.agenda.application.medico.usecase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fiap.agenda.application.medico.controller.dto.AgendaResponseDTO;
import com.fiap.agenda.application.medico.controller.dto.CadastrarAgendaDTO;
import com.fiap.agenda.domain.gateway.IAgendaGateway;
import com.fiap.agenda.domain.usecase.ICadastrarAgenda;
import com.fiap.agenda.infra.persistence.mapper.AgendaMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CadastrarAgenda implements ICadastrarAgenda {

    private final IAgendaGateway agendaGateway;

    private final AgendaMapper agendaMapper;

    @Override
    public AgendaResponseDTO executar(CadastrarAgendaDTO cadastrarAgendaDTO) {
        var agenda = agendaMapper.toAgenda(cadastrarAgendaDTO);
        //TODO trocar pelo valor do token
        agenda.setIdMedico(UUID.fromString("de6a1db3-bdb1-4ec3-9704-6d9583844e3a")); 
        agenda = agendaGateway.salvar(agenda); 

        return agendaMapper.toAgendaResponseDTO(agenda);
    }

}
