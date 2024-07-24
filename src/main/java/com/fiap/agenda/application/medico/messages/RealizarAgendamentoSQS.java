package com.fiap.agenda.application.medico.messages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class RealizarAgendamentoSQS {

    private final SqsTemplate sqsTemplate;

    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.uri}")
    private String endpoint;

    public void enviarConsultaParaAngendamento(String resumo) {
        try {
            String json = objectMapper.writeValueAsString(resumo);
            sqsTemplate.send(endpoint, MessageBuilder.withPayload(json).build());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }


}
