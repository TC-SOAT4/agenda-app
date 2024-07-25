package com.fiap.agenda.domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoConsulta {

    private UUID idMedico;
    private UUID idPaciente;

    private LocalDate dia;
    private LocalTime inicio;
    private LocalTime fim;

    private Long idHorario;
    
}
