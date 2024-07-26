package com.fiap.agenda.domain.client.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoResponse {

    private UUID id;
    private String nome;
    private String crm;

}
