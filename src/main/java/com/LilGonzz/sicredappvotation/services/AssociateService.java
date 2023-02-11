package com.LilGonzz.sicredappvotation.services;

import com.LilGonzz.sicredappvotation.repositories.AssociateRepository;
import com.LilGonzz.sicredappvotation.model.Associate;
import com.LilGonzz.sicredappvotation.model.DTOs.AssociateDTO;
import com.LilGonzz.sicredappvotation.utils.exceptions.GenericNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    AssociateRepository repository;

    public List<AssociateDTO> getAllAssociate(){
        return repository.findAll().stream().map(associate -> new AssociateDTO(associate)).collect(Collectors.toList());
    }

    public AssociateDTO getAssociateById(Integer id){
        Associate associate = repository.findById(id).orElseThrow( () -> new GenericNotFoundException("not found associate with id: "+ id));
        return convertToDto(associate);
    }

    public Associate createAssociate(AssociateDTO associateDto){
        associateDto.setId(null);
        Associate associate = new Associate(associateDto);
        return repository.save(associate);
    }

    public AssociateDTO softDeleteAssociate(Integer id){
        Associate associate = repository.findById(id).orElseThrow( () -> new GenericNotFoundException("doesn't exists associate with id: "+ id));
        associate.setIsActive(false);
        associate.setDeletedAt(LocalDateTime.now());
        AssociateDTO dto = new AssociateDTO(repository.save(associate));
        return dto;
    }

    public void hardDeleteAssociate(Integer id){
        repository.deleteById(id);
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
        return new AssociateDTO(associate.getId(), associate.getFullName(), associate.getDocument());
    }

}
