package com.fiap.globalsolution.beachreport.model.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioUpdateDTO {

    @NotBlank
    @NotNull
    private String senha;
}
