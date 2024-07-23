package com.fiap.agenda.domain.gateway;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fiap.agenda.domain.entity.Agenda;

public interface IAgendaGateway {

    public Agenda salvar(Agenda agendaEntity);
    public Agenda alterar(Agenda agendaEntity);
    public List<Agenda> buscarPorMedicoDia(UUID idMedico, LocalDate dia);
    public void confirmarAgendamento(UUID idAgenda, Long idHorario);
    public Agenda buscarPorId(UUID idAgenda);
}
