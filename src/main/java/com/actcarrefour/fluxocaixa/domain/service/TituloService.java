package com.actcarrefour.fluxocaixa.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.actcarrefour.fluxocaixa.domain.exception.ResourceBadRequestException;
import com.actcarrefour.fluxocaixa.domain.exception.ResourceNotFoundException;
import com.actcarrefour.fluxocaixa.domain.model.Titulo;
import com.actcarrefour.fluxocaixa.domain.model.Usuario;
import com.actcarrefour.fluxocaixa.domain.repository.TituloRepository;
import com.actcarrefour.fluxocaixa.dto.titulo.TituloRequestDto;
import com.actcarrefour.fluxocaixa.dto.titulo.TituloResponseDto;

@Service
public class TituloService implements InterfaceCrudSrv<TituloRequestDto, TituloResponseDto> {

    @Autowired
    private TituloRepository tituloRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<TituloResponseDto> pesquisarTodos() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Titulo> titulos = tituloRepository.findByUsuario(usuario);

        return titulos.stream()
                .map(titulo -> mapper.map(titulo, TituloResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TituloResponseDto pesquisarPorId(Long id) {
        Optional<Titulo> optTitulo = tituloRepository.findById(id);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (optTitulo.isEmpty() || optTitulo.get().getUsuario().getId() != usuario.getId()) {
            throw new ResourceNotFoundException(
                    "Não foi possível encontrar o titulo com ID " + id + " para este usuário logado...");
        }

        return mapper.map(optTitulo.get(), TituloResponseDto.class);
    }

    @Override
    public TituloResponseDto incluir(TituloRequestDto dto) {

        validarTitulo(dto);

        Titulo titulo = mapper.map(dto, Titulo.class);

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        titulo.setUsuario(usuario);
        titulo.setId(null);
        titulo.setDataCadastro(new Date());
        titulo = tituloRepository.save(titulo);

        return mapper.map(titulo, TituloResponseDto.class);
    }

    @Override
    public TituloResponseDto atualizar(Long id, TituloRequestDto dto) {

        pesquisarPorId(id);
        validarTitulo(dto);

        Titulo titulo = mapper.map(dto, Titulo.class);

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        titulo.setUsuario(usuario);
        titulo.setId(id);
        titulo = tituloRepository.save(titulo);

        return mapper.map(titulo, TituloResponseDto.class);
    }

    @Override
    public void excluir(Long id) {
        pesquisarPorId(id);
        // Exclusão física do registro no BD em função da exclusão em cascata de ambas
        // as tabelas usando a FK do título e centro de custo
        tituloRepository.deleteById(id);
    }

    public List<TituloResponseDto> pesquisarPorDataVencimento(String dataInicial, String dataFinal) {
        List<Titulo> titulos = tituloRepository.pesquisarFluxoCaixaPorDataVencimento(dataInicial, dataFinal);

        return titulos.stream()
                .map(titulo -> mapper.map(titulo, TituloResponseDto.class))
                .collect(Collectors.toList());
    }

    private void validarTitulo(TituloRequestDto dto) {

        if (dto.getTipo() == null ||
                dto.getDataVencimento() == null ||
                dto.getValor() == null ||
                dto.getDescricao() == null) {

            throw new ResourceBadRequestException(
                    "Os campos Tipo, Data de Vencimento, Valor e Descrição são obrigatórios...");
        }
    }

}
