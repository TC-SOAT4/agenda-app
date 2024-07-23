package com.fiap.agenda.application.medico.controller.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarAgendaDTO {

    @NotNull
    private LocalDate dia;

    @NotEmpty
    private List<CadastroHorarioDTO> horarios;

}
