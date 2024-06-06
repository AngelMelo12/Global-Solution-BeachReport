package com.fiap.globalsolution.beachreport.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.globalsolution.beachreport.model.Relato;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RelatoDTO(
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
        BigDecimal longitude,

        @NotNull
        @NotBlank
        @Size(max = 1)
        @JsonProperty("praia_suja")
        String praiaSuja,

        @NotNull
        @NotBlank
        @Size(max = 1)
        @JsonProperty("envolve_animais")
        String envolveAnimais,

        @NotNull
        @JsonProperty("id_usuario")
        Long idUsuario
) {

    public Relato toModel() {
        return Relato.builder()
                .foto(new byte[5000])
                .relato(relato)
                .latitude(latitude)
                .longitude(longitude)
                .praiaSuja(praiaSuja)
                .envolveAnimais(envolveAnimais)
                .dataHoraRelato(LocalDateTime.now())
                .nrLikes(0L)
                .build();
    }

}


