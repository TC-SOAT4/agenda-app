package com.fiap.agenda.domain.messaging;

import com.fiap.agenda.domain.entity.AgendamentoConsulta;

public interface IAgendamentoConsultaQueueAdapter {

    public void enviar(AgendamentoConsulta agendamentoConsulta);

}
