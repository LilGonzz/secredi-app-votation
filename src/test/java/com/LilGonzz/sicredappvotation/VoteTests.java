package com.LilGonzz.sicredappvotation;

import com.LilGonzz.sicredappvotation.model.DTOs.AssociateDTO;
import com.LilGonzz.sicredappvotation.model.DTOs.PautaDTO;
import com.LilGonzz.sicredappvotation.model.DTOs.SessionVoteDTO;
import com.LilGonzz.sicredappvotation.model.DTOs.VoteDTO;
import com.LilGonzz.sicredappvotation.model.enums.VoteSelection;
import com.LilGonzz.sicredappvotation.services.AssociateService;
import com.LilGonzz.sicredappvotation.services.PautaService;
import com.LilGonzz.sicredappvotation.services.SessionVoteService;
import com.LilGonzz.sicredappvotation.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoteTests {
    @Autowired
    PautaService pautaService;
    @Autowired
    SessionVoteService sessionVoteService;
    @Autowired
    AssociateService associateService;
    @Autowired
    VoteService voteService;

    public void shouldVote(){
        AssociateDTO associateDTO = associateService.createAssociate(new AssociateDTO(null, "Associado de teste", "11111111"));
        PautaDTO pautaDTO = pautaService.createPauta(new PautaDTO(null, "Pauta de teste", "teste unitário"));
        SessionVoteDTO sessionVoteDTO = sessionVoteService.createSessionVote(new SessionVoteDTO(null, "sessão de teste", "25/05/2023", 0l, 0l, 0l, pautaDTO.getId()));

        VoteDTO voteDTO = voteService.voteInSession(new VoteDTO(null, VoteSelection.YES, sessionVoteDTO.getId(), associateDTO.getId()));
        boolean idExists = voteDTO.getId() != null;

        assertEquals(idExists, true);
    }
}
