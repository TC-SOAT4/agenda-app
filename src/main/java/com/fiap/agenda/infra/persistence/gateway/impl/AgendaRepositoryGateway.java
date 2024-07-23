package com.fiap.agenda.infra.persistence.gateway.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fiap.agenda.domain.entity.Agenda;
import com.fiap.agenda.domain.gateway.IAgendaGateway;
import com.fiap.agenda.exceptions.HorarioNaoEncontradoException;
import com.fiap.agenda.infra.persistence.mapper.AgendaMapper;
import com.fiap.agenda.infra.persistence.repository.AgendaEntityRepository;
import com.fiap.agenda.infra.persistence.repository.HorarioEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AgendaRepositoryGateway implements IAgendaGateway {

    private final AgendaEntityRepository agendaEntityRepository;
    private final HorarioEntityRepository horarioEntityRepository;
    private final AgendaMapper agendaMapper;

    @Override
    public Agenda salvar(Agenda agenda) {
        var agendaEntity = agendaMapper.toAgendaEntity(agenda);
        agendaEntity.setAtivo(Boolean.TRUE);
        agendaEntity.setAgendaNosHorarios();
        agendaEntity.getHorarios().forEach(horario -> horario.setDisponivel(Boolean.TRUE));
        agendaEntity = agendaEntityRepository.save(agendaEntity);
        return agendaMapper.toAgenda(agendaEntity);
    }

    @Override
    public Agenda alterar(Agenda agenda) {
        var agendaEntity = agendaMapper.toAgendaEntity(agenda);
        agendaEntity.setAgendaNosHorarios();
        agendaEntity.getHorarios().forEach(horario -> {
            if(horario.getDisponivel() == null){
                horario.setDisponivel(Boolean.TRUE);
            }
        });
        agendaEntity = agendaEntityRepository.save(agendaEntity);
        return agendaMapper.toAgenda(agendaEntity);
    }

    @Override
    public List<Agenda> buscarPorMedicoDia(UUID idMedico, LocalDate dia) {
        var list = agendaEntityRepository.findAllByIdMedicoAndDia(idMedico, dia);
        return agendaMapper.toAgendaList(list);
    }

    @Override
    public void confirmarAgendamento(UUID idAgenda, Long idHorario) {
        var horarioEntity = horarioEntityRepository.findByIdHorarioAndAgendaIdAgenda(idHorario, idAgenda);
        if (horarioEntity == null)
            throw new HorarioNaoEncontradoException();

        horarioEntity.setDisponivel(Boolean.FALSE);
        horarioEntityRepository.save(horarioEntity);
    }

    @Override
    public Agenda buscarPorId(UUID idAgenda) {
        var agendaEntity = agendaEntityRepository.findById(idAgenda).orElseThrow();
        return agendaMapper.toAgenda(agendaEntity);
    }

}
