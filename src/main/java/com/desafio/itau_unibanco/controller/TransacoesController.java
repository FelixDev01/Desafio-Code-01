package com.desafio.itau_unibanco.controller;

import com.desafio.itau_unibanco.Transacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacoesController {

    @PostMapping
    public ResponseEntity<Void> efetuarTransacao(@RequestBody Transacao dadosTransacao){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity listarTransacoes(){

    }
}
