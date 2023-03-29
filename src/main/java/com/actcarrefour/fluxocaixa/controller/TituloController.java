package com.actcarrefour.fluxocaixa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actcarrefour.fluxocaixa.domain.service.TituloService;
import com.actcarrefour.fluxocaixa.dto.titulo.TituloRequestDto;
import com.actcarrefour.fluxocaixa.dto.titulo.TituloResponseDto;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/titulos")
public class TituloController {

    @Autowired
    private TituloService tituloService;

    @GetMapping
    public ResponseEntity<List<TituloResponseDto>> pesquisarTodos() {
        return ResponseEntity.ok(tituloService.pesquisarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TituloResponseDto> pesquisarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tituloService.pesquisarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TituloResponseDto> incluir(@RequestBody TituloRequestDto dto) {
        TituloResponseDto titulo = tituloService.incluir(dto);
        return new ResponseEntity<>(titulo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TituloResponseDto> atualizar(@PathVariable Long id, @RequestBody TituloRequestDto dto) {
        return ResponseEntity.ok(tituloService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        tituloService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
