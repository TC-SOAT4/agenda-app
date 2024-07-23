package com.fiap.agenda.infra.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.fiap.agenda.application.medico.controller.dto.AgendaResponseDTO;
import com.fiap.agenda.application.medico.controller.dto.AlterarHorarioDTO;
import com.fiap.agenda.application.medico.controller.dto.CadastrarAgendaDTO;
import com.fiap.agenda.domain.entity.Agenda;
import com.fiap.agenda.domain.entity.Horario;
import com.fiap.agenda.infra.persistence.entity.AgendaEntity;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgendaMapper {

    public Agenda toAgenda(CadastrarAgendaDTO cadastrarAgendaDTO);

    public AgendaEntity toAgendaEntity(Agenda agenda);

    public Agenda toAgenda(AgendaEntity agendaEntity);

    public AgendaResponseDTO toAgendaResponseDTO(Agenda agenda);

    public List<Agenda> toAgendaList(List<AgendaEntity> list);

    public List<AgendaResponseDTO> toAgendaResponseDTOList(List<Agenda> list);

    public Horario toHorario(AlterarHorarioDTO horarioEncontrado);

}
