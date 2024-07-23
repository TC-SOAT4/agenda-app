package com.fiap.agenda.application.medico.controller.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorarioResponseDTO {

    private Long idHorario;

    private LocalTime inicio;
    private LocalTime fim;

    private Boolean disponivel;

}
