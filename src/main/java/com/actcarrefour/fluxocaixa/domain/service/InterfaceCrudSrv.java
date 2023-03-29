package com.actcarrefour.fluxocaixa.domain.service;

import java.util.List;

public interface InterfaceCrudSrv<Req, Res> {

    List<Res> pesquisarTodos();

    Res pesquisarPorId(Long id);

    Res incluir(Req dto);

    Res atualizar(Long id, Req dto);

    void excluir(Long id);
}
