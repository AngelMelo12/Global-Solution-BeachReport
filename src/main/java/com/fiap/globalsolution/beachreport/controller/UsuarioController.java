package com.fiap.globalsolution.beachreport.controller;

import com.fiap.globalsolution.beachreport.model.Usuario;
import com.fiap.globalsolution.beachreport.model.dto.UsuarioDTO;
import com.fiap.globalsolution.beachreport.model.dto.UsuarioUpdateDTO;
import com.fiap.globalsolution.beachreport.service.UsuarioService;
import com.fiap.globalsolution.beachreport.utils.URIBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@CacheConfig(cacheNames = "usuarios")
@Tag(name = "usuarios", description = "Endpoint relacionado aos dados de usuários")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Cacheable
    @Operation(summary = "Lista todas as usuarios", description = "Endpoint retorna de forma paginada todos as usuarios, por padrão cada pagina contém 10 cadastros, porém estes dados são parametrizáveis.")
    public ResponseEntity<Page<Usuario>> index(@ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(usuarioService.index(pageable));
    }

    @PostMapping
    @Transactional
    @CacheEvict(allEntries = true)
    @Operation(summary = "Cria uma nova usuario no sistema", description = "Endpoint recebe no corpo da requisição os dados necessários para realizar uma nova usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados"),
            @ApiResponse(responseCode = "201", description = "Usuario criada com sucesso!")
    })
    public ResponseEntity<Usuario> create(@RequestBody @Valid UsuarioDTO usuarioRequest) {
        log.info("Cadastrando usuario: {}", usuarioRequest);
        var emailEmpresa = usuarioService.create(usuarioRequest);
        return ResponseEntity
                .created(URIBuilder.createFromId(emailEmpresa.getId()))
                .build();
    }

    @GetMapping("{id}")
    @Operation(summary = "Exibe os detalhes de uma usuario de id equivalente", description = "Endpoint retorna dados de uma usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Usuario não encontrada"),
            @ApiResponse(responseCode = "200", description = "Usuario detalhada com sucesso!")
    })
    public ResponseEntity<Usuario> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);
        return usuarioService
                .get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(summary = "Deleta uma usuario do sistema", description = "Endpoint recebe no path o id da usuario a ser deletada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Usuario não encontrada"),
            @ApiResponse(responseCode = "204", description = "Usuario removida com sucesso!")
    })
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando usuario por id: {}", id);
        usuarioService.destroy(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    @Transactional
    @CacheEvict(allEntries = true)
    @Operation(summary = "Atualiza uma usuario no sistema", description = "Endpoint recebe no corpo da requisição os dados necessários para atualizar uma usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados"),
            @ApiResponse(responseCode = "200", description = "Usuario atualizada com sucesso!")
    })
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody UsuarioUpdateDTO usuarioRequest){
        log.info("Atualizando e-mail de empresa id {} para {}", id, usuarioRequest);
        return ResponseEntity.ok(usuarioService.update(id, usuarioRequest));
    }
}
