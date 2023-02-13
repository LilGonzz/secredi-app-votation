package com.LilGonzz.sicredappvotation.resources;

import com.LilGonzz.sicredappvotation.model.Associate;
import com.LilGonzz.sicredappvotation.model.DTOs.AssociateDTO;
import com.LilGonzz.sicredappvotation.services.AssociateService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/associates")
public class AssociateResource {

    @Autowired
    AssociateService service;
    @Operation(
            description = "irá buscar todos associados no banco de dados"
    )
    @GetMapping
    public ResponseEntity<List<AssociateDTO>> getAllAssociate(){
            List<AssociateDTO> associates = service.getAllAssociateDTO();
            return ResponseEntity.ok(associates);
    }
    @Operation(
            description = "irá buscar associado no banco de dados pelo id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<AssociateDTO> getAssociateById(@PathVariable final Integer id){
            AssociateDTO associate = service.getAssociateByIdDTO(id);
            return ResponseEntity.ok(associate);
    }
    @Operation(
            description = "criará um associado na base de dados"
    )
    @PostMapping
    public ResponseEntity<AssociateDTO> createAssociate(@RequestBody final AssociateDTO associateDto){
        AssociateDTO associate = service.createAssociate(associateDto);
        URI uri = URI.create("/associates/" + associate.getId());
        return ResponseEntity.created(uri).body(associate);
    }
    @Operation(
            description = "desativará o associado pelo id no banco de dados"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<AssociateDTO> softDeteleAssociate(@PathVariable final Integer id){
       AssociateDTO associate = service.softDeleteAssociate(id);
       return ResponseEntity.ok(associate);
    }
    @Operation(
            description = "deleterá o associado do banco de dados"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> hardDeleteAssociate(@PathVariable final Integer id){
        service.hardDeleteAssociate(id);
        return ResponseEntity.noContent().build();
    }
}
