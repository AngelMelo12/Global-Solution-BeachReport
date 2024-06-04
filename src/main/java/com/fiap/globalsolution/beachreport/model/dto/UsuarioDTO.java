package com.fiap.globalsolution.beachreport.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioDTO {

    @NotNull(message = "CPF deve estar preenchido corretamente!")
    private Long cpf;

    @NotNull
    @NotBlank
    private String senha;
}
