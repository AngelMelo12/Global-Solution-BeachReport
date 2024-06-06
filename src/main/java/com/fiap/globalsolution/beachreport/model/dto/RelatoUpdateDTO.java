package com.fiap.globalsolution.beachreport.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record RelatoUpdateDTO(
        byte[] foto,

        @NotNull
        @NotBlank
        @Size(min = 10, message = "Relato deve ser maior que 10 caracteres!")
        String relato,

        @NotNull
        @DecimalMin(value = "0.0", message = "Valor deve ser maior que 0.0!")
        BigDecimal latitude,

        @NotNull
        @DecimalMin(value = "0.0", message = "Valor deve ser maior que 0.0!")
        BigDecimal longitude
) { }
