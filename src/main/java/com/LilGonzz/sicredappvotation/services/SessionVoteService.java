package com.LilGonzz.sicredappvotation.services;

import com.LilGonzz.sicredappvotation.model.Pauta;
import com.LilGonzz.sicredappvotation.model.SessionVote;
import com.LilGonzz.sicredappvotation.model.DTOs.SessionVoteDTO;
import com.LilGonzz.sicredappvotation.repositories.SessionVoteRepository;
import com.LilGonzz.sicredappvotation.utils.exceptions.GenericNotFoundException;
import com.LilGonzz.sicredappvotation.utils.exceptions.PautaAlreadyInSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionVoteService {

    @Autowired
    SessionVoteRepository repository;
    @Autowired
    PautaService pautaService;

    public List<SessionVoteDTO> getAllSessionVoteActiveDTO(){
        return repository.findByIsActive(true).stream().map(SessionVote -> new SessionVoteDTO(SessionVote)).collect(Collectors.toList());
    }
    public List<SessionVote> getAllSessionVoteActive(){
        return repository.findByIsActive(true);
    }
    public SessionVoteDTO getSessionVoteById(Integer id){
        SessionVote SessionVote = repository.findById(id).orElseThrow( () -> new GenericNotFoundException("not found SessionVote with id: "+ id));
        return convertToDto(SessionVote);
    }

    public SessionVote createSessionVote(SessionVoteDTO dto){
        Pauta pauta = pautaService.findRealById(dto.getPautaId());
        if(pauta == null){
           throw new GenericNotFoundException("não foi possível criar sessão pois não existe pauta com o id informado");
        }
        else if(pauta.getSessionVote() != null){
            throw new PautaAlreadyInSessionException("pauta desejada já está atrelada uma sessão");
        }
        dto.setId(null);
        SessionVote sessionVote = new SessionVote(dto, pauta);
        return repository.save(sessionVote);
    }

    public SessionVoteDTO softDeleteSessionVote(Integer id){
        SessionVote SessionVote = repository.findById(id).orElseThrow( () -> new GenericNotFoundException("doesn't exists SessionVote with id: "+ id));
        SessionVote.setIsActive(false);
        SessionVote.setDeletedAt(LocalDateTime.now());
        SessionVoteDTO dto = new SessionVoteDTO(repository.save(SessionVote));
        return dto;
    }

    public void hardDeleteSessionVote(Integer id){
        SessionVote SessionVote = repository.findById(id).orElseThrow( () -> new GenericNotFoundException("doesn't exists SessionVote with id: "+ id));
        repository.deleteById(id);
    }
    public void checkIfNotExpired(SessionVote session){
        if(session.getLimitDate().isBefore(LocalDateTime.now())){
            session.setIsActive(false);
            session.setDeletedAt(LocalDateTime.now());
            repository.save(session);
        }

    }
    private SessionVoteDTO convertToDto(SessionVote SessionVote){
        return new SessionVoteDTO(SessionVote.getId(), SessionVote.getDescription(), SessionVote.getLimitDate(), SessionVote.getTotalVotes(), SessionVote.getTotalYes(), SessionVote.getTotalNo(), SessionVote.getPauta().getId());
    }
}
