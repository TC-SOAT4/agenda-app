package com.fiap.agenda.domain.entity;

import java.time.LocalTime;
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
public class Horario {

    private Long idHorario;

    private UUID idAgenda;

    private LocalTime inicio;
    private LocalTime fim;

    private Boolean disponivel;

}
