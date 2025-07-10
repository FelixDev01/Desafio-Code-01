package com.desafio.itau_unibanco.service;

import com.desafio.itau_unibanco.Transacao;
import org.hibernate.validator.internal.constraintvalidators.hv.AbstractScriptAssertValidator;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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
}
