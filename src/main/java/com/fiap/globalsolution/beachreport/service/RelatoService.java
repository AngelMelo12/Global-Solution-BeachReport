package com.fiap.globalsolution.beachreport.service;

import com.fiap.globalsolution.beachreport.model.Relato;
import com.fiap.globalsolution.beachreport.model.Usuario;
import com.fiap.globalsolution.beachreport.model.dto.RelatoDTO;
import com.fiap.globalsolution.beachreport.model.dto.RelatoResponseDTO;
import com.fiap.globalsolution.beachreport.model.dto.RelatoUpdateDTO;
import com.fiap.globalsolution.beachreport.model.dto.UsuarioUpdateDTO;
import com.fiap.globalsolution.beachreport.repository.RelatoRepository;
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
public class RelatoService {

    private final RelatoRepository relatoRepository;
    private final UsuarioRepository usuarioRepository;

    public Page<RelatoResponseDTO> index(Pageable pageable) {
        return relatoRepository.findAll(pageable)
                .map(RelatoResponseDTO::fromRelato);
    }

    public RelatoResponseDTO create(RelatoDTO relatoRequest) {
        var usuario = verificarSeExisteUsuario(relatoRequest.idUsuario());
        var newRelato = relatoRequest.toModel();
        newRelato.setUsuario(usuario);
        return RelatoResponseDTO.fromRelato(relatoRepository.save(newRelato));
    }

    public Optional<Relato> get(Long id) {
        return relatoRepository.findById(id);
    }

    public void destroy(Long id) {
        var relato = verificarSeExisteRelato(id);
        relatoRepository.deleteById(relato.getId());
    }

    public RelatoResponseDTO update(Long id, RelatoUpdateDTO relatoUpdateRequest){
        var relatoToUpdate = verificarSeExisteRelato(id);

        relatoToUpdate.setFoto(relatoUpdateRequest.foto() == null ? relatoToUpdate.getFoto() : relatoUpdateRequest.foto());
        relatoToUpdate.setRelato(relatoUpdateRequest.relato());
        relatoToUpdate.setLatitude(relatoUpdateRequest.latitude());
        relatoToUpdate.setLongitude(relatoUpdateRequest.longitude());

        return RelatoResponseDTO.fromRelato(relatoRepository.save(relatoToUpdate));
    }

    public RelatoResponseDTO likeRelato(Long id) {
        var relatoToLike = verificarSeExisteRelato(id);
        relatoToLike.like();
        return RelatoResponseDTO.fromRelato(relatoRepository.save(relatoToLike));
    }

    private Usuario verificarSeExisteUsuario(Long id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado" )
                );
    }

    private Relato verificarSeExisteRelato(Long id) {
        return relatoRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
                );
    }
}
