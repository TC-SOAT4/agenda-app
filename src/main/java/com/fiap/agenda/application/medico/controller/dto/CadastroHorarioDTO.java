package com.fiap.agenda.application.medico.controller.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroHorarioDTO {

    @JsonFormat(pattern="HH:mm:ss")
    @Schema(type = "string", format = "HH:mm:ss", example = "14:30:00")
    @NotNull
    private LocalTime inicio;

    @JsonFormat(pattern="HH:mm:ss")
    @Schema(type = "string", format = "HH:mm:ss", example = "15:30:00")
    @NotNull
    private LocalTime fim;
}
