package com.ctw.monitoramentoagricola.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Objeto de transferência de dados que representa a leitura completa dos sensores da estufa")
public record EstufaDTO(
        @Schema(description = "Identificador único da leitura no banco de dados", example = "101")
        Long id,
        @Schema(description = "Temperatura ambiente em graus Celsius", example = "28.5")
        BigDecimal temperatura,
        @Schema(description = "Umidade relativa do ar em porcentagem (%)", example = "65.0")
        BigDecimal umidade,
        @Schema(description = "Pressão atmosférica em hPa", example = "1013.2")
        BigDecimal pressao,
        @Schema(description = "Indicador de presença no local (0: Ausente, 1: Presente)", example = "0")
        Integer presenca,
        @Schema(description = "Distância medida pelo sensor ultrassônico (cm)", example = "150")
        Integer distancia,
        @Schema(description = "Nível de luminosidade ambiente (LDR)", example = "800")
        Integer luminosidade,
        @Schema(description = "Temperatura do solo em graus Celsius", example = "22.3")
        Integer temp_solo,
        @Schema(description = "Data e hora exata da coleta dos dados", example = "2026-03-16T14:30:00")
        LocalDateTime dataHora
) {}
