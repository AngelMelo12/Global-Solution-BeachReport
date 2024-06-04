package com.fiap.globalsolution.beachreport.repository;

import com.fiap.globalsolution.beachreport.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
