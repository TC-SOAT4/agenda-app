package com.fiap.agenda.application.controller.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovoAgendamentoConsultaDTO {

    @NotNull
    private UUID idMedico;

    @NotNull
    private Long idHorario;

}
