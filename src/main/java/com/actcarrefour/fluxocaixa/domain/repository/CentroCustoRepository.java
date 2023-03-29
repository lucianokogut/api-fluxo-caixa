package com.actcarrefour.fluxocaixa.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actcarrefour.fluxocaixa.domain.model.CentroCusto;
import com.actcarrefour.fluxocaixa.domain.model.Usuario;

public interface CentroCustoRepository extends JpaRepository<CentroCusto, Long> {

    List<CentroCusto> findByUsuario(Usuario usuario);
}
