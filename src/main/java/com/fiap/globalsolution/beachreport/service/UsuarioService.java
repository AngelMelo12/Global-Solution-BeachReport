package com.fiap.globalsolution.beachreport.service;

import com.fiap.globalsolution.beachreport.model.EmailUsuario;
import com.fiap.globalsolution.beachreport.model.Endereco;
import com.fiap.globalsolution.beachreport.model.Telefone;
import com.fiap.globalsolution.beachreport.model.Usuario;
import com.fiap.globalsolution.beachreport.model.dto.UsuarioDTO;
import com.fiap.globalsolution.beachreport.model.dto.UsuarioResponseDTO;
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

    public Page<UsuarioResponseDTO> index(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(UsuarioResponseDTO::fromUsuario);
    }

    public UsuarioResponseDTO create(UsuarioDTO usuarioRequest) {
        return UsuarioResponseDTO.fromUsuario(usuarioRepository.save(UsuarioDTO.toUsuario(usuarioRequest)));
    }

    public Optional<Usuario> get(Long id) {
        return usuarioRepository.findById(id);
    }

    public void destroy(Long id) {
        var usuario = verificarSeExisteUsuario(id);
        usuarioRepository.deleteById(usuario.getId());
    }

    public UsuarioResponseDTO update(Long id, UsuarioUpdateDTO usuarioRequest){
        var usuarioToUpdate = verificarSeExisteUsuario(id);

        var email = usuarioToUpdate.getEmailUsuario();
        email.setEmail(usuarioRequest.email());

        var endereco = usuarioToUpdate.getEndereco();
        endereco.setCep(usuarioRequest.cep());

        var telefone = usuarioToUpdate.getTelefone();
        telefone.setDdd(usuarioRequest.ddd());
        telefone.setTelefone(usuarioRequest.telefone());

        usuarioToUpdate.setSenha(usuarioRequest.senha());
        usuarioToUpdate.setEmailUsuario(email);
        usuarioToUpdate.setEndereco(endereco);
        usuarioToUpdate.setTelefone(telefone);

        return UsuarioResponseDTO.fromUsuario(usuarioRepository.save(usuarioToUpdate));
    }

    private Usuario verificarSeExisteUsuario(Long id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado" )
                );
    }
}
