package com.fiap.agenda.application.medico.controller.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovoAgendamentoConsultaDTO {

    private UUID idMedico;
    private Long idHorario;

}
