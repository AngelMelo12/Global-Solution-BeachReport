package com.fiap.globalsolution.beachreport.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.sql.Date;

@Entity
@Table(name = "T_TB_RELATO")
@Data
public class Relato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_relato")
    private Long id;

    @Column(name = "blob_foto")
    private Blob foto;

    @Column(name = "ds_relato")
    private String relato;

    @Column(name = "nr_latitude")
    private Number latitude;

    @Column(name = "nr_longitude")
    private Number longitude;

    @Column(name = "praia_suja")
    private String praiaSuja;

    @Column(name = "envolve_animais")
    private String envolveAnimais;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "dt_hr_relato")
    private Date dataHoraRelato;

    @Column(name = "nr_likes")
    private Number nrLikes;
}
