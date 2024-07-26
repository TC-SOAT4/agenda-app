package com.fiap.agenda.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.agenda.application.controller.dto.AgendaResponseDTO;
import com.fiap.agenda.application.controller.dto.NovoAgendamentoConsultaDTO;
import com.fiap.agenda.domain.usecase.IRealizarAgendamentoDeConsulta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Consulta")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final IRealizarAgendamentoDeConsulta realizarAgendamentoDeConsulta;

    @PreAuthorize("hasRole('PACIENTE')")
    @Operation(summary = "Agendar Consulta", description = "Agendar uma nova consulta")
    @PostMapping
    public ResponseEntity<AgendaResponseDTO> agendar(@Valid @RequestBody NovoAgendamentoConsultaDTO novoAgendamentoConsultaDTO,  @Schema(hidden=true) @RequestHeader("Authorization") String bearerToken) {
        realizarAgendamentoDeConsulta.executar(novoAgendamentoConsultaDTO, bearerToken);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
