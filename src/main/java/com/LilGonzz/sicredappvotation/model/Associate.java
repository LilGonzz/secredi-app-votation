package com.LilGonzz.sicredappvotation.model;

import com.LilGonzz.sicredappvotation.model.DTOs.AssociateDTO;
import com.LilGonzz.sicredappvotation.model.abstractClasses.BaseClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Associate extends BaseClass {
    private String fullName;
    private String document;
    @JsonIgnore
    @OneToMany(mappedBy = "associate")
    private Set<Vote> votes;
    @ManyToMany
    @JoinTable(
            name = "associates_and_sessions",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "associate_id"))
    private Set<SessionVote> sessions;

    public Associate(Integer id, String fullName, String document){
        super(id, LocalDateTime.now(), true, null);
        this.fullName = fullName;
        this.document = document;
    }

    public Associate(AssociateDTO dto){
        super(dto.getId(), LocalDateTime.now(), true, null);
        this.fullName = dto.getFullName();
        this.document = dto.getDocument();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Associate associate)) return false;
        if (!super.equals(o)) return false;
        return document.equals(associate.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), document);
    }
    
}
