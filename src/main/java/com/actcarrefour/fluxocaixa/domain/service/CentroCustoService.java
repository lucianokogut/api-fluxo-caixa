package com.actcarrefour.fluxocaixa.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.actcarrefour.fluxocaixa.domain.exception.ResourceNotFoundException;
import com.actcarrefour.fluxocaixa.domain.model.CentroCusto;
import com.actcarrefour.fluxocaixa.domain.model.Usuario;
import com.actcarrefour.fluxocaixa.domain.repository.CentroCustoRepository;
import com.actcarrefour.fluxocaixa.dto.centrocusto.CentroCustoRequestDto;
import com.actcarrefour.fluxocaixa.dto.centrocusto.CentroCustoResponseDto;

@Service
public class CentroCustoService implements InterfaceCrudSrv<CentroCustoRequestDto, CentroCustoResponseDto> {

    @Autowired
    private CentroCustoRepository centroCustoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CentroCustoResponseDto> pesquisarTodos() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CentroCusto> lista = centroCustoRepository.findByUsuario(usuario);

        return lista.stream()
                .map(centroCusto -> mapper.map(centroCusto, CentroCustoResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CentroCustoResponseDto pesquisarPorId(Long id) {
        Optional<CentroCusto> optCentroCusto = centroCustoRepository.findById(id);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (optCentroCusto.isEmpty() || optCentroCusto.get().getUsuario().getId() != usuario.getId()) {
            throw new ResourceNotFoundException(
                    "Não foi possível encontrar o centro de custo com ID " + id + " para este usuário logado...");
        }

        return mapper.map(optCentroCusto.get(), CentroCustoResponseDto.class);
    }

    @Override
    public CentroCustoResponseDto incluir(CentroCustoRequestDto dto) {
        CentroCusto centroCusto = mapper.map(dto, CentroCusto.class);

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        centroCusto.setUsuario(usuario);

        centroCusto.setId(null);

        centroCusto = centroCustoRepository.save(centroCusto);

        return mapper.map(centroCusto, CentroCustoResponseDto.class);
    }

    @Override
    public CentroCustoResponseDto atualizar(Long id, CentroCustoRequestDto dto) {

        pesquisarPorId(id);
        CentroCusto centroCusto = mapper.map(dto, CentroCusto.class);

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        centroCusto.setUsuario(usuario);

        centroCusto.setId(id);
        centroCusto = centroCustoRepository.save(centroCusto);

        return mapper.map(centroCusto, CentroCustoResponseDto.class);
    }

    @Override
    public void excluir(Long id) {
        pesquisarPorId(id);
        // Exclusão física do registro no BD para demonstrar outra forma do "delete
        // lógico"
        centroCustoRepository.deleteById(id);
    }

}
