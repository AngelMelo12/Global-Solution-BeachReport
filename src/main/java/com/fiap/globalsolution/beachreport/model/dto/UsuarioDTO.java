package com.fiap.globalsolution.beachreport.model.dto;

import com.fiap.globalsolution.beachreport.model.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record UsuarioDTO (

    @NotNull(message = "CPF deve estar preenchido corretamente!")
    Long cpf,

    @NotNull
    @NotBlank
    String senha,

    @NotNull
    @NotBlank
    @Email
    String email,

    @NotNull
    Long cep,

    @NotNull
    Long ddd,

    @NotNull
    Long telefone
) {

    public static Usuario toUsuario(UsuarioDTO dto) {

        var email = EmailUsuario.builder()
                .email(dto.email)
                .build();

        var endereco = Endereco.builder()
                .cep(dto.cep)
                .build();

        var telefone = Telefone.builder()
                .ddd(dto.ddd)
                .telefone(dto.telefone)
                .build();

        var nivelConfiabilidade = NivelConfiabilidade.builder()
                .nivelConfiabilidade(0L)
                .build();

        var usuario = Usuario.builder()
                .cpf(dto.cpf)
                .senha(dto.senha)
                .nivelConfiabilidade(nivelConfiabilidade)
                .relatos(new ArrayList<>())
                .build();

        email.setUsuario(usuario);
        endereco.setUsuario(usuario);
        telefone.setUsuario(usuario);
        nivelConfiabilidade.setUsuario(usuario);

        usuario.setEmailUsuario(email);
        usuario.setEndereco(endereco);
        usuario.setTelefone(telefone);

        return usuario;
    }
}
