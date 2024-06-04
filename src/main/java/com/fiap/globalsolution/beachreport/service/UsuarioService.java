package com.fiap.globalsolution.beachreport.service;

import com.fiap.globalsolution.beachreport.model.Usuario;
import com.fiap.globalsolution.beachreport.model.dto.UsuarioDTO;
import com.fiap.globalsolution.beachreport.model.dto.UsuarioUpdateDTO;
import com.fiap.globalsolution.beachreport.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Page<Usuario> index(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario create(UsuarioDTO usuarioRequest) {

        var newUsuario = new Usuario();
        newUsuario.setCpf(usuarioRequest.getCpf());
        newUsuario.setSenha(usuarioRequest.getSenha());

        return usuarioRepository.save(newUsuario);
    }

    public Optional<Usuario> get(Long id) {
        return usuarioRepository.findById(id);
    }

    public void destroy(Long id) {
        verificarSeExisteUsuario(id);
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Long id, UsuarioUpdateDTO usuarioRequest){
        var usuarioToUpdate = verificarSeExisteUsuario(id);
        usuarioToUpdate.setSenha(usuarioRequest.getSenha());
        return usuarioRepository.save(usuarioToUpdate);
    }

    private Usuario verificarSeExisteUsuario(Long id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado" )
                );
    }
}
