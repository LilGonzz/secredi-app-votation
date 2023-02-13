package com.LilGonzz.sicredappvotation;

import com.LilGonzz.sicredappvotation.model.DTOs.PautaDTO;
import com.LilGonzz.sicredappvotation.services.PautaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PautaTests {

    @Autowired
    PautaService service;
    @Test
    public void shouldCreateNewPauta(){
        PautaDTO dto = new PautaDTO(null, "Nome da pauta", "Essa é uma pauta de teste");
        PautaDTO saved = service.createPauta(dto);
        boolean idExists = saved.getId() != null;
        assertEquals(idExists, true);
    }

}
