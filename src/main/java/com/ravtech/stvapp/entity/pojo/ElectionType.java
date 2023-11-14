package com.ravtech.stvapp.entity.pojo;

public enum ElectionType {
    RCV ("ranked choice voting"),
    STAR ("score then automatic runoff");

    private final String fullName;

    ElectionType(String fullName) {
        this.fullName = fullName;
    }

    public String getElectionType() {
        return fullName;
    }
}
