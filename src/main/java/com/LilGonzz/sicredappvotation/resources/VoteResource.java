package com.LilGonzz.sicredappvotation.resources;

import com.LilGonzz.sicredappvotation.model.DTOs.VoteDTO;
import com.LilGonzz.sicredappvotation.model.Vote;
import com.LilGonzz.sicredappvotation.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteResource {
    @Autowired
    VoteService service;
    @GetMapping("/{id}")
    public ResponseEntity<VoteDTO> getVoteById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getVoteById(id));
    }
    @GetMapping
    public ResponseEntity<List<VoteDTO>> getAllVotesOrBy(@RequestParam Integer associateId, @RequestParam Integer sessionId){
        List<VoteDTO> votes = service.getAllVotes(associateId, sessionId);
        return ResponseEntity.ok(votes);
    }
    @PostMapping
    public ResponseEntity<Vote> voteInSession(@RequestBody VoteDTO dto){
        Vote vote = service.voteInSession(dto);
        return ResponseEntity.ok(vote);
    }
}
