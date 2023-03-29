package com.actcarrefour.fluxocaixa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.actcarrefour.fluxocaixa.domain.service.DashboardService;
import com.actcarrefour.fluxocaixa.dto.dashboard.DashboardResponseDto;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponseDto> pesquisarFluxoCaixa(
            @RequestParam(name = "dataInicial") String dataInicial,
            @RequestParam(name = "dataFinal") String dataFinal) {
        return ResponseEntity.ok(dashboardService.pesquisarFluxoCaixa(dataInicial, dataFinal));
    }
}
