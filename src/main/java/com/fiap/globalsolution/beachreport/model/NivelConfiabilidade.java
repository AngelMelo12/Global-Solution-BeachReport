package com.fiap.globalsolution.beachreport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_BR_NIVEL_CONFIABILIDADE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NivelConfiabilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_nivel_confiabilidade")
    private Long id;

    @Column(name = "nr_nivel_confiabilidade")
    private Long nivelConfiabilidade;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "T_BR_USUARIO_id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
}
