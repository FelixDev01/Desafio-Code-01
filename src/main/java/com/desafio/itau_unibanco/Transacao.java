package com.desafio.itau_unibanco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transacao {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
