package com.fiap.agenda.application.controller.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarAgendaDTO {

    private Boolean ativo;

    @NotEmpty
    private List<AlterarHorarioDTO> horarios;

}
