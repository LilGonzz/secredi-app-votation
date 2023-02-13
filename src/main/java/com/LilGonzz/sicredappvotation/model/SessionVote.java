package com.LilGonzz.sicredappvotation.model;

import com.LilGonzz.sicredappvotation.model.DTOs.SessionVoteDTO;
import com.LilGonzz.sicredappvotation.model.abstractClasses.BaseClass;
import com.LilGonzz.sicredappvotation.utils.exceptions.GenericDateException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class SessionVote extends BaseClass {

    private String description;
    private LocalDateTime limitDate;
    private Long totalVotes;
    private Long totalYes;
    private Long totalNo;
    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;
    @JsonIgnore
    @OneToMany(mappedBy = "session")
    private List<Vote> votes;
    @JsonIgnore
    @ManyToMany(mappedBy = "sessions")
    private Set<Associate> associates;


    public SessionVote(Integer id, String description, LocalDateTime limitDate, Long totalVotes, Long totalYes, Long totalNo, Pauta pauta) {
        super(id, LocalDateTime.now(), true, null);
        this.description = description;
        this.limitDate = limitDate;
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

    private LocalDateTime checkDate(String limitDate){
        LocalDateTime dateTime = convertToDate(limitDate);
        if (dateTime == null)
            return LocalDateTime.now().plusSeconds(60);

        if (dateTime.isBefore(LocalDateTime.now()))
            throw new GenericDateException("data inválida");

        return dateTime;
    }
    private LocalDateTime convertToDate(String limitDate){
        try {
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateWithoutTime = LocalDate.parse(limitDate, formatterDate);
            return dateWithoutTime.atTime(23, 59);
        } catch (Exception ex){
            throw new GenericDateException("data inválida");
        }
    }

}
