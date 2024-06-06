package com.fiap.globalsolution.beachreport.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record UsuarioUpdateDTO(
        @NotBlank
        @NotNull
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
) { }
