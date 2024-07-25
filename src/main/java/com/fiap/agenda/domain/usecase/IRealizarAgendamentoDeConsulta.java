package com.fiap.agenda.domain.usecase;

import com.fiap.agenda.application.medico.controller.dto.NovoAgendamentoConsultaDTO;

public interface IRealizarAgendamentoDeConsulta {

    public void executar(NovoAgendamentoConsultaDTO novoAgendamentoConsultaDTO);

}
