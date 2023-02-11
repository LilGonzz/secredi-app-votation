package com.LilGonzz.sicredappvotation.model;

import com.LilGonzz.sicredappvotation.model.DTOs.AssociateDTO;
import com.LilGonzz.sicredappvotation.model.abstractClasses.BaseClass;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Associate extends BaseClass {
    private String fullName;
    private String document;

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
