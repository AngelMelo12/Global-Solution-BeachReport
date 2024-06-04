package com.fiap.globalsolution.beachreport.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "T_TB_USUARIO")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nr_cpf")
    private Long cpf;

    @Column(name = "varchar_senha")
    private String senha;
}
