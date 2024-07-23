package com.fiap.agenda.exceptions;

public class HorarioNaoEncontradoException extends RuntimeException {
    public HorarioNaoEncontradoException() {
        super("Horário não encontrado");
    }
}
