package com.fiap.globalsolution.beachreport.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.globalsolution.beachreport.model.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record UsuarioResponseDTO(
        Long id,

        Long cpf,

        String email,

        Long cep,

        Long ddd,

        Long telefone,

        @JsonProperty("nivel_confiabilidade")
        Long nivelConfiabilidade,

        List<RelatoResponseDTO> relatos
) {

    

    public static UsuarioResponseDTO fromUsuario(Usuario usuario) {

        var telefone = usuario.getTelefone();
        var relatos = usuario.getRelatos();
        var confiabilidade = usuario.getNivelConfiabilidade();

        List<RelatoResponseDTO> relatosResponse = new ArrayList<>();

        if (!relatos.isEmpty()) {
            relatosResponse = relatos.stream()
                    .map(RelatoResponseDTO::fromRelato)
                    .toList();
        }

        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .cpf(usuario.getCpf())
                .email(usuario.getEmailUsuario().getEmail())
                .cep(usuario.getEndereco().getCep())
                .ddd(telefone.getDdd())
                .telefone(telefone.getTelefone())
                .nivelConfiabilidade(confiabilidade.getNivelConfiabilidade())
                .relatos(relatosResponse)
                .build();
    }
}
