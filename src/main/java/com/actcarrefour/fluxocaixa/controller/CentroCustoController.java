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

import com.actcarrefour.fluxocaixa.domain.service.CentroCustoService;
import com.actcarrefour.fluxocaixa.dto.centrocusto.CentroCustoRequestDto;
import com.actcarrefour.fluxocaixa.dto.centrocusto.CentroCustoResponseDto;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/centrocustos")
public class CentroCustoController {

    @Autowired
    private CentroCustoService centroCustoService;

    @GetMapping
    public ResponseEntity<List<CentroCustoResponseDto>> pesquisarTodos() {
        return ResponseEntity.ok(centroCustoService.pesquisarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroCustoResponseDto> pesquisarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(centroCustoService.pesquisarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CentroCustoResponseDto> incluir(@RequestBody CentroCustoRequestDto dto) {
        CentroCustoResponseDto centroCusto = centroCustoService.incluir(dto);
        return new ResponseEntity<>(centroCusto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroCustoResponseDto> atualizar(@PathVariable Long id,
            @RequestBody CentroCustoRequestDto dto) {
        return ResponseEntity.ok(centroCustoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        centroCustoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
