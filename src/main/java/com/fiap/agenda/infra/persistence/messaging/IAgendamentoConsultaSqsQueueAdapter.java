package com.fiap.agenda.infra.persistence.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.agenda.domain.entity.AgendamentoConsulta;
import com.fiap.agenda.domain.messaging.IAgendamentoConsultaQueueAdapter;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class IAgendamentoConsultaSqsQueueAdapter implements IAgendamentoConsultaQueueAdapter {

    private final SqsTemplate sqsTemplate;

    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.uri}")
    private String uriSqs;

    @Override
    public void enviar(AgendamentoConsulta agendamentoConsulta) {
        try {
            String json = objectMapper.writeValueAsString(agendamentoConsulta);
            sqsTemplate.send(uriSqs, MessageBuilder.withPayload(json).build());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    
}
