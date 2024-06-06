package com.fiap.globalsolution.beachreport.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "T_BR_USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nr_cpf")
    private Long cpf;

    @Column(name = "varchar_senha")
    private String senha;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private EmailUsuario emailUsuario;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Telefone telefone;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private NivelConfiabilidade nivelConfiabilidade;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Relato> relatos;

    public void aumentaConfiabilidade() {
        var nivelConfiabilidade = this.getNivelConfiabilidade();
        var totalConfiabilidade = nivelConfiabilidade.getNivelConfiabilidade();
        nivelConfiabilidade.setNivelConfiabilidade(totalConfiabilidade + 1);
    }
}
