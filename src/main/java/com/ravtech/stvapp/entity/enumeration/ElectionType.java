package com.ravtech.stvapp.entity.enumeration;

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
