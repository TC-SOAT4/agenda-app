package com.fiap.agenda.domain.entity;

import java.time.LocalTime;

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
public class Horario {

    private Long idHorario;

    private Agenda agenda;

    private LocalTime inicio;
    private LocalTime fim;

    private Boolean disponivel;

}
