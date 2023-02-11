package com.LilGonzz.sicredappvotation.model.DTOs;

import com.LilGonzz.sicredappvotation.model.Vote;
import com.LilGonzz.sicredappvotation.model.enums.VoteSelection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteDTO {
    private Integer id;
    private VoteSelection selection;
    private Integer sessionVoteId;
    private Integer associateId;

    public VoteDTO(Integer id, VoteSelection selection, Integer sessionVoteId, Integer associateId) {
        this.id = id;
        this.selection = selection;
        this.sessionVoteId = sessionVoteId;
        this.associateId = associateId;
    }
    public VoteDTO(Vote vote){
        id = vote.getId();
        selection = vote.getSelection();
        sessionVoteId = vote.getSession().getId();
        associateId = vote.getAssociate().getId();
    }
}
