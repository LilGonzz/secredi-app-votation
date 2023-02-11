package com.LilGonzz.sicredappvotation.model.DTOs;

import com.LilGonzz.sicredappvotation.model.Pauta;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PautaDTO {
    private Integer id;
    private String pautaName;
    private String description;

    public PautaDTO(Integer id, String pautaName, String description){
        this.id = id;
        this.pautaName = pautaName;
        this.description = description;
    }
    public PautaDTO(Pauta pauta){
        id = pauta.getId();
        pautaName = pauta.getPautaName();
        description = pauta.getDescription();
    }
}