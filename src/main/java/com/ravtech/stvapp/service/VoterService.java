package com.ravtech.stvapp.service;

import com.ravtech.stvapp.entity.Election;

import java.util.List;

public interface VoterService {
    int getVoterIdByUsername(String username);
    List<Election> getElections(String username);
}
