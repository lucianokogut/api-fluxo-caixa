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

import com.actcarrefour.fluxocaixa.domain.service.UsuarioService;
import com.actcarrefour.fluxocaixa.dto.usuario.UsuarioRequestDto;
import com.actcarrefour.fluxocaixa.dto.usuario.UsuarioResponseDto;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> pesquisarTodos() {
        return ResponseEntity.ok(usuarioService.pesquisarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> pesquisarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.pesquisarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> incluir(@RequestBody UsuarioRequestDto dto) {
        UsuarioResponseDto usuario = usuarioService.incluir(dto);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDto dto) {
        UsuarioResponseDto usuario = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
