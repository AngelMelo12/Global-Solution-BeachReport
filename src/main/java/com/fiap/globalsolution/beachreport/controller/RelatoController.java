package com.fiap.globalsolution.beachreport.controller;

import com.fiap.globalsolution.beachreport.model.dto.RelatoDTO;
import com.fiap.globalsolution.beachreport.model.dto.RelatoResponseDTO;
import com.fiap.globalsolution.beachreport.model.dto.RelatoUpdateDTO;
import com.fiap.globalsolution.beachreport.service.RelatoService;
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
@RequestMapping("relato")
@CacheConfig(cacheNames = "relatos")
@Tag(name = "relatos", description = "Endpoint relacionado aos dados de usuários")
@Slf4j
public class RelatoController {

    @Autowired
    private RelatoService relatoService;

    @GetMapping
    @Cacheable
    @Operation(summary = "Lista todos os relatos", description = "Endpoint retorna de forma paginada todos as relatos, por padrão cada pagina contém 10 cadastros, porém estes dados são parametrizáveis.")
    public ResponseEntity<Page<RelatoResponseDTO>> index(@ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(relatoService.index(pageable));
    }

    @PostMapping
    @Transactional
    @CacheEvict(allEntries = true)
    @Operation(summary = "Cria um novo relato no sistema", description = "Endpoint recebe no corpo da requisição os dados necessários para criar um novo relato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados"),
            @ApiResponse(responseCode = "201", description = "Relato criada com sucesso!")
    })
    public ResponseEntity<RelatoResponseDTO> create(@RequestBody @Valid RelatoDTO relatoRequest) {
        log.info("Cadastrando relato: {}", relatoRequest);
        var relato = relatoService.create(relatoRequest);
        return ResponseEntity
                .created(URIBuilder.createFromId(relato.id()))
                .build();
    }

    @GetMapping("{id}")
    @Operation(summary = "Exibe os detalhes de um relato de id equivalente", description = "Endpoint retorna dados de um relato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Relato não encontrada"),
            @ApiResponse(responseCode = "200", description = "Relato detalhada com sucesso!")
    })
    public ResponseEntity<RelatoResponseDTO> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);
        return relatoService
                .get(id)
                .map(RelatoResponseDTO::fromRelato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(summary = "Deleta uma relato do sistema", description = "Endpoint recebe no path o id da relato a ser deletada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Relato não encontrada"),
            @ApiResponse(responseCode = "204", description = "Relato removida com sucesso!")
    })
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando relato por id: {}", id);
        relatoService.destroy(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    @Transactional
    @CacheEvict(allEntries = true)
    @Operation(summary = "Atualiza uma relato no sistema", description = "Endpoint recebe no corpo da requisição os dados necessários para atualizar uma relato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados"),
            @ApiResponse(responseCode = "200", description = "Relato atualizada com sucesso!")
    })
    public ResponseEntity<RelatoResponseDTO> update(@PathVariable Long id, @RequestBody RelatoUpdateDTO relatoRequest){
        log.info("Atualizando e-mail de empresa id {} para {}", id, relatoRequest);
        return ResponseEntity.ok(relatoService.update(id, relatoRequest));
    }

    @PatchMapping("{id}")
    @Transactional
    @CacheEvict(allEntries = true)
    @Operation(summary = "Atualiza uma relato no sistema", description = "Endpoint recebe no corpo da requisição os dados necessários para atualizar uma relato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados"),
            @ApiResponse(responseCode = "200", description = "Relato atualizada com sucesso!")
    })
    public ResponseEntity<RelatoResponseDTO> like(@PathVariable Long id){
        log.info("Inserindo like em relato de id: {}", id);
        return ResponseEntity.ok(relatoService.likeRelato(id));
    }
}
