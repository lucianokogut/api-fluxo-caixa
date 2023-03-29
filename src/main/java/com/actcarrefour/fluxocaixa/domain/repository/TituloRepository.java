package com.actcarrefour.fluxocaixa.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.actcarrefour.fluxocaixa.domain.model.Titulo;
import com.actcarrefour.fluxocaixa.domain.model.Usuario;

public interface TituloRepository extends JpaRepository<Titulo, Long> {

        @Query(nativeQuery = true, value = "SELECT * FROM titulo " +
                        "WHERE data_vencimento " +
                        "BETWEEN TO_TIMESTAMP(:dataInicial, 'YYYY-MM-DD hh24:MI:SS') AND " +
                        "TO_TIMESTAMP(:dataFinal, 'YYYY-MM-DD hh24:MI:SS')")
        List<Titulo> pesquisarFluxoCaixaPorDataVencimento(
                        @Param("dataInicial") String dataInicial,
                        @Param("dataFinal") String dataFinal);

        List<Titulo> findByUsuario(Usuario usuario);
}
