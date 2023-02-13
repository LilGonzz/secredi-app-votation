package com.LilGonzz.sicredappvotation;


import com.LilGonzz.sicredappvotation.model.DTOs.PautaDTO;
import com.LilGonzz.sicredappvotation.model.DTOs.SessionVoteDTO;
import com.LilGonzz.sicredappvotation.services.PautaService;
import com.LilGonzz.sicredappvotation.services.SessionVoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class SessionVoteTests {

    @Autowired
    SessionVoteService sessionVoteService;
    @Autowired
    PautaService pautaService;


    @Test
    public void shouldCreateNewSessionVote(){

        PautaDTO pautaDTO = pautaService.createPauta(new PautaDTO(null, "Pauta de teste", "teste unitário"));
        SessionVoteDTO sessionVoteDTO = sessionVoteService.createSessionVote(new SessionVoteDTO(null, "sessão de teste", "25/05/2023", 0l, 0l, 0l, pautaDTO.getId()));

        boolean idExists = sessionVoteDTO.getId() != null;

        assertEquals(idExists, true);
    }
}
