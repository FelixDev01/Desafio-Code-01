package com.desafio.itau_unibanco.controller;

import com.desafio.itau_unibanco.Transacao;
import com.desafio.itau_unibanco.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransacoesController {

    private final TransacaoService transacaoService;

    public TransacoesController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> efetuarTransacao(@RequestBody Transacao transacao){

        if(transacao.getValor() == null || transacao.getDataHora() == null){
            return ResponseEntity.unprocessableEntity().build();
        }

        if(transacao.getDataHora().isAfter(OffsetDateTime.now())){
            return ResponseEntity.unprocessableEntity().build();
        }

        if(transacao.getValor().compareTo(BigDecimal.ZERO) < 0){
            return ResponseEntity.unprocessableEntity().build();
        }

        transacaoService.adicionarTransacao(transacao);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*@GetMapping
    public ResponseEntity listarTransacoes(){

    }*/

    @DeleteMapping
    public ResponseEntity deletarRegistros(){
        transacaoService.deletarTransacao();
        return ResponseEntity.ok().build();
    }
}
