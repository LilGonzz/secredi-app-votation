package com.LilGonzz.sicredappvotation.model.DTOs;

import com.LilGonzz.sicredappvotation.model.SessionVote;
import com.LilGonzz.sicredappvotation.utils.exceptions.GenericDateException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class SessionVoteDTO {
    private Integer id;
    private String description;
    private String limitDate;
    private Long totalVotes;
    private Long totalYes;
    private Long totalNo;
    private Boolean isActive;
    private Integer pautaId;

    public SessionVoteDTO(Integer id, String description, String limitDate, Long totalVotes, Long totalYes, Long totalNo, Integer pautaId) {
        this.id = id;
        this.description = description;
        this.limitDate = limitDate;
        this.totalVotes = totalVotes == null ? 0 : totalVotes;
        this.totalYes = totalYes == null ? 0 : totalYes;
        this.totalNo = totalNo == null ? 0 : totalNo;
        this.pautaId = pautaId;
        this.isActive = true;
    }

    public SessionVoteDTO(SessionVote sessionVote){
        id = sessionVote.getId();
        description = sessionVote.getDescription();
        limitDate = sessionVote.getLimitDate().toString();
        totalVotes = sessionVote.getTotalVotes() == null ? 0 : sessionVote.getTotalVotes();
        totalYes = sessionVote.getTotalYes() == null ? 0 : sessionVote.getTotalYes();
        totalNo = sessionVote.getTotalNo() == null ? 0 : sessionVote.getTotalNo();
        pautaId = sessionVote.getPauta().getId();
        isActive = sessionVote.getIsActive();
    }

    private LocalDateTime checkDate(String limitDate){
        LocalDateTime dateTime = convertToDate(limitDate);
        if (dateTime == null)
            return LocalDateTime.now().plusSeconds(60);

        if (dateTime.isBefore(LocalDateTime.now()))
            throw new GenericDateException("data inválida");

        return dateTime;
    }

    private LocalDateTime convertToDate(String limitDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDateTime.parse(limitDate, formatter);
    }
}
