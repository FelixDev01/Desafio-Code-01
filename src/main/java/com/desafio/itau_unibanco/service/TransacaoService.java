package com.desafio.itau_unibanco.service;

import com.desafio.itau_unibanco.Estatistica;
import com.desafio.itau_unibanco.Transacao;
import org.hibernate.validator.internal.constraintvalidators.hv.AbstractScriptAssertValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransacaoService {

    private final Queue<Transacao> transacoes =new ConcurrentLinkedQueue<>();

    public void adicionarTransacao (Transacao transacao){
        transacoes.add(transacao);
    }

    public void deletarTransacao () {
        transacoes.clear();
    }

    public Queue<Transacao> buscarTodasAsTransacoesUltimos60Seg() {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime limite = agora.minusSeconds(60);

        return transacoes.stream()
                .filter(t -> t.getDataHora().isAfter(limite))
                .collect(ConcurrentLinkedQueue::new, Queue::add, Queue::addAll);
    }

    public Queue<Transacao> getTodasTransacoes() {
        return transacoes;
    }

    public Estatistica calcularEstatisticasDosUltimos60Segundos() {
        Queue<Transacao> ultimas = buscarTodasAsTransacoesUltimos60Seg();

        long count = ultimas.size();

        if (count == 0) {
            return new Estatistica(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        BigDecimal sum = ultimas.stream()
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal avg = sum.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);

        BigDecimal min = ultimas.stream()
                .map(Transacao::getValor)
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        BigDecimal max = ultimas.stream()
                .map(Transacao::getValor)
                .max(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        return new Estatistica(count, sum, avg, min, max);
    }
}
