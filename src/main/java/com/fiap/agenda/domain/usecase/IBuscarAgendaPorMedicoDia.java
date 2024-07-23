package com.fiap.agenda.domain.usecase;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fiap.agenda.application.medico.controller.dto.AgendaResponseDTO;

public interface IBuscarAgendaPorMedicoDia {

    public List<AgendaResponseDTO> executar(UUID idMedico, LocalDate dia);

}
