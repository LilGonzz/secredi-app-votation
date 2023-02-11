package com.LilGonzz.sicredappvotation.services;

import com.LilGonzz.sicredappvotation.model.Associate;
import com.LilGonzz.sicredappvotation.model.Pauta;
import com.LilGonzz.sicredappvotation.model.DTOs.PautaDTO;
import com.LilGonzz.sicredappvotation.model.SessionVote;
import com.LilGonzz.sicredappvotation.repositories.PautaRepository;
import com.LilGonzz.sicredappvotation.utils.exceptions.GenericNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PautaService {

    @Autowired
    PautaRepository repository;

    public List<PautaDTO> findAllPautas(){
        return repository.findAll().stream().map(pauta -> new PautaDTO(pauta)).collect(Collectors.toList());
    }

    public PautaDTO findById(Integer id){
        Pauta pauta = repository.findById(id).orElseThrow( () -> new GenericNotFoundException("not find pauta for id: + " + id));

        return convertToDto(pauta);
    }

    public Pauta findRealById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public void saveSessionInPauta(Pauta pauta, SessionVote sessionVote){
        pauta.setSessionVote(sessionVote);
        repository.save(pauta);
    }

    public Pauta createPauta(PautaDTO PautaDTO){
        PautaDTO.setId(null);
        Pauta Pauta = new Pauta(PautaDTO);
        return repository.save(Pauta);
    }

    public PautaDTO softDeletePauta(Integer id){
        Pauta Pauta = repository.findById(id).orElseThrow( () -> new GenericNotFoundException("doesn't exists pauta for id: + " + id));
        Pauta.setIsActive(false);
        Pauta.setDeletedAt(LocalDateTime.now());
        PautaDTO dto = new PautaDTO(repository.save(Pauta));
        return dto;
    }
    public void hardDeletePauta(Integer id){
        repository.deleteById(id);
    }
    private PautaDTO convertToDto(Pauta pauta){
        return new PautaDTO(pauta.getId(), pauta.getPautaName(), pauta.getDescription());
    }

}
