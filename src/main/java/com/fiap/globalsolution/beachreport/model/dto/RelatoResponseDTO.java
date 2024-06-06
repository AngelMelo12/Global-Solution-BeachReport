package com.fiap.globalsolution.beachreport.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.globalsolution.beachreport.model.Relato;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record RelatoResponseDTO(
        Long id,

        Long autor,

        byte[] foto,

        String relato,

        BigDecimal latitude,

        BigDecimal longitude,

        @JsonProperty("praia_suja")
        String praiaSuja,

        @JsonProperty("envolve_animais")
        String envolveAnimais,

        @JsonProperty("data_hora_relato")
        LocalDateTime dataHoraRelato,

        @JsonProperty("numero_likes")
        Long likes
) {

    public static RelatoResponseDTO fromRelato(Relato relato) {
        return RelatoResponseDTO.builder()
                .id(relato.getId())
                .autor(relato.getUsuario().getCpf())
                .foto(relato.getFoto())
                .relato(relato.getRelato())
                .latitude(relato.getLatitude())
                .longitude(relato.getLongitude())
                .praiaSuja(relato.getPraiaSuja())
                .envolveAnimais(relato.getEnvolveAnimais())
                .dataHoraRelato(relato.getDataHoraRelato())
                .likes(relato.getNrLikes())
                .build();
    }

}
