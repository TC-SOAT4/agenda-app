package com.fiap.agenda.application.medico.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.agenda.application.medico.controller.dto.AgendaResponseDTO;
import com.fiap.agenda.application.medico.controller.dto.AlterarAgendaDTO;
import com.fiap.agenda.application.medico.controller.dto.CadastrarAgendaDTO;
import com.fiap.agenda.domain.usecase.IAlterarAgenda;
import com.fiap.agenda.domain.usecase.IBuscarAgendaPorMedicoDia;
import com.fiap.agenda.domain.usecase.ICadastrarAgenda;
import com.fiap.agenda.domain.usecase.IConfirmarAgendamento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Agenda")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/agendas")
public class AgendaController {

    private final ICadastrarAgenda cadastrarAgenda;
    private final IBuscarAgendaPorMedicoDia buscarAgendaPorMedicoDia;
    private final IConfirmarAgendamento confirmarAgendamento;
    private final IAlterarAgenda alterarAgenda;

    @PreAuthorize("hasRole('PACIENTE')")
    @Operation(summary = "Buscar por Médico/Dia", description = "Listar agenda do medico para o dia informado")
    @GetMapping
    public ResponseEntity<List<AgendaResponseDTO>> buscarPorMedico(
            @Schema(type = "string", example = "de6a1db3-bdb1-4ec3-9704-6d9583844e3a") @RequestParam(required = true, name = "idMedico") UUID idMedico,
            @Schema(type = "string", format = "aaaa-mm-dd", example = "2024-07-23") @RequestParam(required = true, name = "data") LocalDate data) {
        return ResponseEntity.ok(buscarAgendaPorMedicoDia.executar(idMedico, data));
    }

    @PreAuthorize("hasRole('MEDICO')")
    @Operation(summary = "Criar Agenda", description = "Criar uma agenda com os horários disponíveis")
    @PostMapping
    public ResponseEntity<AgendaResponseDTO> criar(@Valid @RequestBody CadastrarAgendaDTO cadastrarAgendaDTO) {
        return ResponseEntity.ok(cadastrarAgenda.executar(cadastrarAgendaDTO));
    }

    @PreAuthorize("hasRole('MEDICO')")
    @Operation(summary = "Alterar Agenda", description = "Alterar uma agenda é os horários disponíveis")
    @PutMapping("/{idAgenda}")
    public ResponseEntity<AgendaResponseDTO> alterar(@PathVariable("idAgenda") UUID idAgenda,
            @RequestBody @Valid AlterarAgendaDTO alterarAgendaDTO) {
        return ResponseEntity.ok(alterarAgenda.executar(idAgenda, alterarAgendaDTO));
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @Operation(summary = "Confirmar Horario", description = "Confirma horario de agendamento previamente realizado")
    @PostMapping("/{idAgenda}/horarios/{idHorario}")
    public ResponseEntity<Object> confirmarAgendamento(@PathVariable("idAgenda") UUID idAgenda,
            @PathVariable("idHorario") Long idHorario) {
        confirmarAgendamento.executar(idAgenda, idHorario);
        return ResponseEntity.ok().build();
    }

}
