package com.LilGonzz.sicredappvotation.resources;

import com.LilGonzz.sicredappvotation.model.Associate;
import com.LilGonzz.sicredappvotation.model.DTOs.AssociateDTO;
import com.LilGonzz.sicredappvotation.services.AssociateService;
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
    @GetMapping
    public ResponseEntity<List<AssociateDTO>> getAllAssociate(){
            List<AssociateDTO> associates = service.getAllAssociateDTO();
            return ResponseEntity.ok(associates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociateDTO> getAssociateById(@PathVariable final Integer id){
            AssociateDTO associate = service.getAssociateById(id);
            return ResponseEntity.ok(associate);
    }
    @PostMapping
    public ResponseEntity<Associate> createAssociate(@RequestBody final AssociateDTO associateDto){
        Associate associate = service.createAssociate(associateDto);
        URI uri = URI.create("/associates/" + associate.getId());
        return ResponseEntity.created(uri).body(associate);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AssociateDTO> softDeteleAssociate(@PathVariable final Integer id){
       AssociateDTO associate = service.softDeleteAssociate(id);
       return ResponseEntity.ok(associate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> hardDeleteAssociate(@PathVariable final Integer id){
        service.hardDeleteAssociate(id);
        return ResponseEntity.noContent().build();
    }
}
