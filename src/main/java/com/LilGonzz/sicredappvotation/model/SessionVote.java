package com.LilGonzz.sicredappvotation.model;

import com.LilGonzz.sicredappvotation.model.DTOs.SessionVoteDTO;
import com.LilGonzz.sicredappvotation.model.abstractClasses.BaseClass;
import com.LilGonzz.sicredappvotation.utils.exceptions.GenericDateException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class SessionVote extends BaseClass {

    private String description;
    private LocalDateTime limitDate;
    private Long totalVotes;
    private Long totalYes;
    private Long totalNo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;


    public SessionVote(Integer id, String description, LocalDateTime limitDate, Long totalVotes, Long totalYes, Long totalNo, Pauta pauta) {
        super(id, LocalDateTime.now(), true, null);
        this.description = description;
        this.limitDate = checkDate(limitDate);
        this.totalVotes = totalVotes;
        this.totalYes = totalYes;
        this.totalNo = totalNo;
        this.pauta = pauta;
    }

    public SessionVote(SessionVoteDTO dto, Pauta pauta){
        super(dto.getId(), LocalDateTime.now(), true, null);
        this.description = dto.getDescription();
        this.limitDate = checkDate(dto.getLimitDate());
        this.totalVotes = dto.getTotalVotes() == null ? 0 : dto.getTotalVotes();
        this.totalYes = dto.getTotalYes() == null ? 0 : dto.getTotalYes();
        this.totalNo = dto.getTotalNo() == null ? 0 : dto.getTotalNo();
        this.pauta = pauta;
    }

    private LocalDateTime checkDate(LocalDateTime limitDate){
        if (limitDate == null)
            return LocalDateTime.now().plusSeconds(60);

        if (limitDate.isBefore(LocalDateTime.now()))
            throw new GenericDateException("data inválida");

        return limitDate;
    }
}
