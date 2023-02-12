package com.LilGonzz.sicredappvotation.resources;

import com.LilGonzz.sicredappvotation.model.SessionVote;
import com.LilGonzz.sicredappvotation.model.DTOs.SessionVoteDTO;
import com.LilGonzz.sicredappvotation.services.SessionVoteService;
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
    @GetMapping
    public ResponseEntity<List<SessionVoteDTO>> getAllSessionVote(){
            List<SessionVoteDTO> SessionVotes = service.getAllSessionVoteActiveDTO();
            return ResponseEntity.ok(SessionVotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionVoteDTO> getSessionVoteById(@PathVariable final Integer id){
            SessionVoteDTO SessionVote = service.getSessionVoteById(id);
            return ResponseEntity.ok(SessionVote);
    }
    @PostMapping
    public ResponseEntity<SessionVoteDTO> createSessionVote(@RequestBody final SessionVoteDTO SessionVoteDto){
        SessionVoteDTO SessionVote = service.createSessionVote(SessionVoteDto);
        URI uri = URI.create("/session-vote/" + SessionVote.getId());
        return ResponseEntity.created(uri).body(SessionVote);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SessionVoteDTO> softDeteleSessionVote(@PathVariable final Integer id){
       SessionVoteDTO SessionVote = service.softDeleteSessionVote(id);
       return ResponseEntity.ok(SessionVote);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> hardDeleteSessionVote(@PathVariable final Integer id){
        service.hardDeleteSessionVote(id);
        return ResponseEntity.noContent().build();
    }
}
