package com.LilGonzz.sicredappvotation.services;

import com.LilGonzz.sicredappvotation.model.SessionVote;
import com.LilGonzz.sicredappvotation.repositories.AssociateRepository;
import com.LilGonzz.sicredappvotation.model.Associate;
import com.LilGonzz.sicredappvotation.model.DTOs.AssociateDTO;
import com.LilGonzz.sicredappvotation.utils.exceptions.AlreadyVoteException;
import com.LilGonzz.sicredappvotation.utils.exceptions.GenericNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociateService {
    @Autowired
    CacheManager cacheManager;
    @Autowired
    AssociateRepository repository;
    public List<AssociateDTO> getAllAssociateDTO(){
        return repository.findAll().stream().map(associate -> new AssociateDTO(associate)).collect(Collectors.toList());
    }
    public AssociateDTO getAssociateByIdDTO(Integer id){
        Associate associate = repository.findById(id).orElseThrow( () -> new GenericNotFoundException("not found associate with id: "+ id));
        return convertToDto(associate);
    }
    @Cacheable(cacheNames = "associate", key = "#id")
    public Associate findByIdAndActive(Integer id){
        return repository.findByIdAndIsActive(id, true)
                .orElseThrow(() -> { throw new GenericNotFoundException("associado não encontrado"); });
    }
    public Associate canVoteById(Integer id, Integer sesionId){
        Associate associate = findByIdAndActive(id);
        for (SessionVote sessionVote : associate.getSessions()){
            if(sessionVote.getId() == sesionId)
                throw new AlreadyVoteException("associado já votou nessa sessão");
        }
        return associate;
    }
    public AssociateDTO createAssociate(AssociateDTO associateDto){
        associateDto.setId(null);
        Associate associate = new Associate(associateDto);
        repository.save(associate);
        return convertToDto(associate);
    }
    @CacheEvict(cacheNames = "associate", key = "#id")
    public AssociateDTO softDeleteAssociate(Integer id){
        Associate associate = repository.findById(id).orElseThrow( () -> new GenericNotFoundException("doesn't exists associate with id: "+ id));
        associate.setIsActive(false);
        associate.setDeletedAt(LocalDateTime.now());
        AssociateDTO dto = new AssociateDTO(repository.save(associate));
        return dto;
    }
    private String canVote(String document){
        WebClient webClient = WebClient.create("https://user-info.herokuapp.com/users");

        var response = webClient.get()
                .uri("/{document}", document)
                .retrieve()
                .onStatus(status -> HttpStatusCode.valueOf(404).equals(status),
                        clientResponse -> Mono.empty())
                .bodyToMono(String.class)
                .block();

        return response;
    }
    private AssociateDTO convertToDto(Associate associate){
        return new AssociateDTO(associate);
    }

}
