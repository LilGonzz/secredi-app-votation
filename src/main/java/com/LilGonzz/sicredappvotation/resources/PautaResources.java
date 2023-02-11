package com.LilGonzz.sicredappvotation.resources;

import com.LilGonzz.sicredappvotation.model.DTOs.PautaDTO;
import com.LilGonzz.sicredappvotation.model.Pauta;
import com.LilGonzz.sicredappvotation.services.PautaService;
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
    @GetMapping
    public ResponseEntity<List<PautaDTO>> getAllPautas(){
        return ResponseEntity.ok(service.findAllPautasDTO());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PautaDTO> getPautaById(@PathVariable final Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pauta> createPauta(@RequestBody final PautaDTO dto){
        Pauta pauta = service.createPauta(dto);
        URI uri = URI.create("/pautas/" + pauta.getId());
        return ResponseEntity.created(uri).body(pauta);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Pauta> softDeletePauta(@PathVariable final Integer id){
        service.softDeletePauta(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> hardDeletePauta(@PathVariable final Integer id){
        service.hardDeletePauta(id);
        return ResponseEntity.noContent().build();
    }
}
