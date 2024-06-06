package com.fiap.globalsolution.beachreport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_BR_RELATO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Relato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_relato")
    private Long id;

    @Lob
    @Column(name = "blob_foto")
    private byte[] foto;

    @Column(name = "ds_relato")
    private String relato;

    @Column(name = "nr_latitude")
    private BigDecimal latitude;

    @Column(name = "nr_longitude")
    private BigDecimal longitude;

    @Column(name = "praia_suja", columnDefinition="char(1)")
    private String praiaSuja;

    @Column(name = "envolve_animais", columnDefinition="char(1)")
    private String envolveAnimais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "T_TB_USUARIO_id_usuario")
    private Usuario usuario;

    @Column(name = "dt_hr_relato")
    private LocalDateTime dataHoraRelato;

    @Column(name = "nr_likes")
    private Long nrLikes;

    public void like() {
        this.nrLikes += 1;
    }
}
