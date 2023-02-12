package com.LilGonzz.sicredappvotation.resources;

import com.LilGonzz.sicredappvotation.model.SessionVote;
import com.LilGonzz.sicredappvotation.model.DTOs.SessionVoteDTO;
import com.LilGonzz.sicredappvotation.services.SessionVoteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/session-vote")
public class SessionVoteResource {

    @Autowired
    SessionVoteService service;
    @Operation(
            description = "buscará todas as sessões de votação abertas na base de dados"
    )
    @GetMapping
    public ResponseEntity<List<SessionVoteDTO>> getAllSessionVote(){
            List<SessionVoteDTO> SessionVotes = service.getAllSessionVoteActiveDTO();
            return ResponseEntity.ok(SessionVotes);
    }
    @Operation(
            description = "buscará uma sessão de votação pelo id na base de dados"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SessionVoteDTO> getSessionVoteById(@PathVariable final Integer id){
            SessionVoteDTO SessionVote = service.getSessionVoteById(id);
            return ResponseEntity.ok(SessionVote);
    }
    @Operation(
            description = "criará uma sessão de votação na base de dados," +
                    " só será criada se a pauta em questão não estiver com sessões atreladas a ela, mesmo se a sessão já estiver finalizada"
    )
    @PostMapping
    public ResponseEntity<SessionVote> createSessionVote(@RequestBody final SessionVoteDTO SessionVoteDto){
        SessionVote SessionVote = service.createSessionVote(SessionVoteDto);
        URI uri = URI.create("/session-vote/" + SessionVote.getId());
        return ResponseEntity.created(uri).body(SessionVote);
    }
    @Operation(
            description = "desativará a sessão de votação pelo id no banco de dados"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<SessionVoteDTO> softDeteleSessionVote(@PathVariable final Integer id){
       SessionVoteDTO SessionVote = service.softDeleteSessionVote(id);
       return ResponseEntity.ok(SessionVote);
    }
    @Operation(
            description = "deleterá a sessão de votação do banco de dados"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> hardDeleteSessionVote(@PathVariable final Integer id){
        service.hardDeleteSessionVote(id);
        return ResponseEntity.noContent().build();
    }
}
