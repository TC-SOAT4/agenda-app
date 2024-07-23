package com.fiap.agenda.domain.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Agenda {

    private UUID idAgenda;

    private LocalDate dia;
    private UUID idMedico;

    private Boolean ativo;

    private List<Horario> horarios;

}
