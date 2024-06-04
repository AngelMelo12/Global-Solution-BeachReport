package com.fiap.globalsolution.beachreport.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Blob;
import java.sql.Date;

@Data
public class RelatoDTO {

    @NotNull(message = "Esse campo deve ser preenchido!")
    private Long id;
    private Blob foto;
    private String relato;
    private Number latitude;
    private Number longitude;
    private String praiaSuja;
    private String envolveAnimais;
    private Date dataHrRelato;
    private Number nrlikes;

    @NotNull
    @NotBlank
    private String senha;
}


