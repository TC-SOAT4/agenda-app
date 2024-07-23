package com.fiap.agenda.domain.usecase;

import com.fiap.agenda.application.medico.controller.dto.AgendaResponseDTO;
import com.fiap.agenda.application.medico.controller.dto.CadastrarAgendaDTO;

public interface ICadastrarAgenda {

    public AgendaResponseDTO executar(CadastrarAgendaDTO cadastrarAgendaDTO);
    
}
