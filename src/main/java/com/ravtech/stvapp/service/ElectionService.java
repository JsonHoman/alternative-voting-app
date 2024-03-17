package com.ravtech.stvapp.service;

import com.ravtech.stvapp.entity.Election;

import java.util.List;

public interface ElectionService {
    Election createElection(Election election);

    List<Election> getVoterElections(String username);
}
