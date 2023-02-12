package com.LilGonzz.sicredappvotation.services;

import com.LilGonzz.sicredappvotation.model.Associate;
import com.LilGonzz.sicredappvotation.model.DTOs.SessionVoteDTO;
import com.LilGonzz.sicredappvotation.model.DTOs.VoteDTO;
import com.LilGonzz.sicredappvotation.model.SessionVote;
import com.LilGonzz.sicredappvotation.model.Vote;
import com.LilGonzz.sicredappvotation.model.enums.VoteSelection;
import com.LilGonzz.sicredappvotation.repositories.VoteRepository;
import com.LilGonzz.sicredappvotation.utils.exceptions.AlreadyVoteException;
import com.LilGonzz.sicredappvotation.utils.exceptions.GenericNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {
    @Autowired
    VoteRepository repository;
    @Autowired
    SessionVoteService sessionService;
    @Autowired
    AssociateService associateService;

    public VoteDTO getVoteById(Integer id){
        Vote vote = repository.findById(id).orElseThrow(() -> new GenericNotFoundException("não encontrado voto para id: " + id));
        return convertToDto(vote);
    }
    public List<VoteDTO> getAllVotes(Integer associateId, Integer sessionId){
        List<Vote> votes;

        if(associateId != null && sessionId != null)
            votes = repository.findByAssociateIdAndSessionId(associateId, sessionId);
        else if(associateId != null && sessionId == null)
            votes = repository.findByAssociateId(associateId);
        else if(associateId == null && sessionId != null)
            votes = repository.findBySessionId(sessionId);
        else
            votes = repository.findAll();

        return votes.stream().map(vote -> new VoteDTO(vote)).collect(Collectors.toList());
    }
    public VoteDTO voteInSession(VoteDTO dto){
        SessionVote sessionVote = sessionService.getActiveSessionVoteById(dto.getSessionVoteId());
        if(sessionVote == null)
            throw new GenericNotFoundException("sessão não encontrada ou já foi finalizada");
        Associate associate = associateService.canVoteById(dto.getAssociateId(), dto.getSessionVoteId());
        countNewVote(sessionVote, dto);
        Vote vote = new Vote(dto);
        vote.setSession(sessionVote);
        vote.setAssociate(associate);
        associate.getSessions().add(sessionVote);
        sessionVote.getAssociates().add(associate);
        repository.save(vote);
        return convertToDto(vote);
    }
    private VoteDTO convertToDto(Vote vote){
        return new VoteDTO(vote);
    }
    private void countNewVote(SessionVote sessionVote, VoteDTO dto){
        if(dto.getSelection() == VoteSelection.YES)
            sessionVote.setTotalYes(sessionVote.getTotalYes() + 1);
        if(dto.getSelection() == VoteSelection.NO)
            sessionVote.setTotalNo(sessionVote.getTotalNo() + 1);
        sessionVote.setTotalVotes(sessionVote.getTotalVotes() + 1);
    }
}
