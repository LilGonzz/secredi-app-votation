package com.LilGonzz.sicredappvotation.model.DTOs;

import com.LilGonzz.sicredappvotation.model.Vote;
import com.LilGonzz.sicredappvotation.model.enums.VoteSelection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteDTO {
    private Integer id;
    @NotNull(message = "o campo voto é necessário")
    private VoteSelection selection;
    @NotNull(message = "o id da sessao é necessário")
    private Integer sessionVoteId;
    @NotNull(message = "o id do associado é necessário")
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
