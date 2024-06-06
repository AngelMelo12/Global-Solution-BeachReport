package com.fiap.globalsolution.beachreport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_BR_ENDERECO_USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_email_contato")
    private Long id;

    @Column(name = "nr_cep")
    private Long cep;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "T_BR_USUARIO_id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
}
