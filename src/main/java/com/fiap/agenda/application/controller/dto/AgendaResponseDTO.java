package com.fiap.agenda.application.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaResponseDTO {

    private UUID idAgenda;

    private LocalDate dia;
    private UUID idMedico;

    private Boolean ativo;

    private List<HorarioResponseDTO> horarios;

}
