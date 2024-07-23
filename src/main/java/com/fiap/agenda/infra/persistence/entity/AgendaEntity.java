package com.fiap.agenda.infra.persistence.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "agenda")
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAgenda;

    private LocalDate dia;
    private UUID idMedico;

    private Boolean ativo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda")
    private List<HorarioEntity> horarios;

    public void setAgendaNosHorarios() {
        horarios.forEach(horario -> horario.setAgenda(this));
    }

}
