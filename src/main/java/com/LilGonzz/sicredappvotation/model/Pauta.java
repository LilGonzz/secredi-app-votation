package com.LilGonzz.sicredappvotation.model;

import com.LilGonzz.sicredappvotation.model.DTOs.PautaDTO;
import com.LilGonzz.sicredappvotation.model.abstractClasses.BaseClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pauta extends BaseClass {

    private String pautaName;
    private String description;
    @JsonIgnore
    @OneToOne(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SessionVote sessionVote;

    public Pauta(Integer id, String pautaName, String description){
        super(id, LocalDateTime.now(), true, null);
        this.pautaName = pautaName;
        this.description = description;
    }

    public Pauta (PautaDTO dto){
        super(dto.getId(), LocalDateTime.now(), true, null);
        this.pautaName = dto.getPautaName();
        this.description = dto.getDescription();
    }

}
