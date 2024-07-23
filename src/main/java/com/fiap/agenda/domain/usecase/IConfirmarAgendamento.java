package com.fiap.agenda.domain.usecase;

import java.util.UUID;

public interface IConfirmarAgendamento {

    void executar(UUID idAgenda, Long idHorario);

}
