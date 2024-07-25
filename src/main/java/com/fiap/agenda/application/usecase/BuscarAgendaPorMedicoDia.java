package com.fiap.agenda.application.usecase;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fiap.agenda.application.controller.dto.AgendaResponseDTO;
import com.fiap.agenda.domain.gateway.IAgendaGateway;
import com.fiap.agenda.domain.usecase.IBuscarAgendaPorMedicoDia;
import com.fiap.agenda.infra.persistence.mapper.AgendaMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BuscarAgendaPorMedicoDia implements IBuscarAgendaPorMedicoDia {

    private final IAgendaGateway agendaGateway;

    private final AgendaMapper agendaMapper;

    @Override
    public List<AgendaResponseDTO> executar(UUID idMedico, LocalDate dia) {
        var list = agendaGateway.buscarPorMedicoDia(idMedico, dia);
        return agendaMapper.toAgendaResponseDTOList(list);
    }

}
