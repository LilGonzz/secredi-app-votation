package com.LilGonzz.sicredappvotation.model.enums;

import lombok.Getter;

@Getter
public enum VoteSelection {

    YES(1, "Sim"),
    NO(2, "Não");

    private final Integer id;
    private final String description;

    private VoteSelection(Integer id, String description){
        this.id = id;
        this.description = description;
    }
}
