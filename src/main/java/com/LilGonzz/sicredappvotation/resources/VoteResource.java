package com.LilGonzz.sicredappvotation.resources;

import com.LilGonzz.sicredappvotation.model.DTOs.VoteDTO;
import com.LilGonzz.sicredappvotation.model.Vote;
import com.LilGonzz.sicredappvotation.services.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteResource {
    @Autowired
    VoteService service;
    @Operation(
            description = "buscará na base de dados um voto pelo id do mesmo"
    )
    @GetMapping("/{id}")
    public ResponseEntity<VoteDTO> getVoteById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getVoteById(id));
    }
    @Operation(
            description = "buscará por todos os votos, se não for passado nada de parametrôs irá buscar todos os votos na base de dados" +
                    "se os parametrôs forem passados, será filtrados por eles"
    )
    @GetMapping
    public ResponseEntity<List<VoteDTO>> getAllVotesOrBy(@RequestParam Integer associateId, @RequestParam Integer sessionId){
        List<VoteDTO> votes = service.getAllVotes(associateId, sessionId);
        return ResponseEntity.ok(votes);
    }
    @Operation(
            description = "será feito um voto se caso o associado não tiver votado na sessão ainda e se a sessão estiver ativa"
    )
    @PostMapping
    public ResponseEntity<VoteDTO> voteInSession(@Valid @RequestBody VoteDTO dto){
        VoteDTO vote = service.voteInSession(dto);
        return ResponseEntity.ok(vote);
    }
}
