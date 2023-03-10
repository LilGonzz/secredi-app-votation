package com.LilGonzz.sicredappvotation.model.DTOs;

import com.LilGonzz.sicredappvotation.model.Associate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssociateDTO {
    private Integer id;
    @NotBlank(message = "o campo nome é necessário")
    private String fullName;
    @NotBlank(message = "o campo documento é necessário")
    private String document;

    public AssociateDTO(Integer id, String fullName, String document){
        this.id = id;
        this.fullName = fullName;
        this.document = document;
    }
    public AssociateDTO(Associate associate){
        id = associate.getId();
        fullName = associate.getFullName();
        document = associate.getDocument();
    }
}
