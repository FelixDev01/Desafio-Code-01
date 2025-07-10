package com.desafio.itau_unibanco.dto;

import java.time.OffsetDateTime;

public record DadosTransacao(Double valor, OffsetDateTime dataHora) {
}
