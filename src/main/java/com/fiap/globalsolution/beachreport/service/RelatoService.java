package com.fiap.globalsolution.beachreport.service;

import com.fiap.globalsolution.beachreport.model.Usuario;
import com.fiap.globalsolution.beachreport.model.dto.RelatoDTO;
import com.fiap.globalsolution.beachreport.model.dto.UsuarioDTO;
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

    public Page<Relato> index(Pageable pageable) {
        return relatoRepository.findAll(pageable);
    }

    public Relato create(RelatoDTO relatoRequest) {

        var newRelato = new Relato();
        newRelato.setId(relatoRequest.getId());
        newRelato.setFoto(relatoRequest.getFoto());
        newRelato.setRelato(relatoRequest.getRelato());
        newRelato.setLatitude(relatoRequest.getLatitude());
        newRelato.setLongitude(relatoRequest.getLongitude());
        newRelato.setPraia_suja(relatoRequest.getPraia_suja());
        newRelato.setEnvolve_animais(relatoRequest.getEnvolve_animais());
        newRelato.setData_hr_relato(relatoRequest.getData_hr_relato());
        newRelato.setNr_likes(relatoRequest.getNr_likes());

        return relatoRepository.save(newRelato);
    }

    public Optional<Relato> get(Long id) {
        return RelatoRepository.findById(id);
    }

    public void destroy(Long id) {
        verificarSeExisteUsuario(id);
        relatoRepository.deleteById(id);
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

}
