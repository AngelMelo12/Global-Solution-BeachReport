package com.fiap.globalsolution.beachreport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_BR_TELEFONE_USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_telefone")
    private Long id;

    @Column(name = "nr_ddd")
    private Long ddd;

    @Column(name = "nr_telefone")
    private Long telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "T_BR_USUARIO_id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
}
