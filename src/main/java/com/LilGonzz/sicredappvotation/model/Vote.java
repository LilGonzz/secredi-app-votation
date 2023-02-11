package com.LilGonzz.sicredappvotation.model;

import com.LilGonzz.sicredappvotation.model.DTOs.VoteDTO;
import com.LilGonzz.sicredappvotation.model.abstractClasses.BaseClass;
import com.LilGonzz.sicredappvotation.model.enums.VoteSelection;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Vote extends BaseClass {

    private VoteSelection selection;

    public Vote(Integer id, VoteSelection selection){
        super(id, LocalDateTime.now(), true, null);
        this.selection = selection;
    }
    public Vote(VoteDTO dto){
        super(dto.getId(), LocalDateTime.now(), true, null);
        selection = dto.getSelection();
    }
}
