package com.LilGonzz.sicredappvotation.model.DTOs;

import com.LilGonzz.sicredappvotation.model.enums.VoteSelection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteDTO {
    private Integer id;
    private VoteSelection selection;

    public VoteDTO(Integer id, VoteSelection selection) {
        this.id = id;
        this.selection = selection;
    }
}
