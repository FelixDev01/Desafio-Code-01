package com.desafio.itau_unibanco.controller;

import com.desafio.itau_unibanco.dto.DadosTransacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacoesController {

    @PostMapping
    public ResponseEntity efetuarTransacao(@RequestBody DadosTransacao dados){
        return ResponseEntity.ok().body(dados);
    }
}
