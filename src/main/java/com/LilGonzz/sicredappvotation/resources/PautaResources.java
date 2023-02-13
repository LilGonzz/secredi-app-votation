package com.LilGonzz.sicredappvotation.resources;

import com.LilGonzz.sicredappvotation.model.DTOs.PautaDTO;
import com.LilGonzz.sicredappvotation.model.Pauta;
import com.LilGonzz.sicredappvotation.services.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaResources {

    @Autowired
    PautaService service;
    @Operation(
            description = "buscará todas as pautas na base de dados"
    )
    @GetMapping
    public ResponseEntity<List<PautaDTO>> getAllPautas(){
        return ResponseEntity.ok(service.findAllPautasDTO());
    }
    @Operation(
            description = "buscará uma pauta pelo id na base de dados"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PautaDTO> getPautaById(@PathVariable final Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            description = "criará uma pauta na base de dados"
    )
    @PostMapping
    public ResponseEntity<PautaDTO> createPauta(@Valid  @RequestBody final PautaDTO dto){
        PautaDTO pauta = service.createPauta(dto);
        URI uri = URI.create("/pautas/" + pauta.getId());
        return ResponseEntity.created(uri).body(pauta);
    }
    @Operation(
            description = "desativará a pauta pelo id no banco de dados"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Pauta> softDeletePauta(@PathVariable final Integer id){
        service.softDeletePauta(id);
        return ResponseEntity.noContent().build();
    }
}
