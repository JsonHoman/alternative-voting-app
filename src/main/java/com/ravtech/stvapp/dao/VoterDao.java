package com.ravtech.stvapp.dao;

import com.ravtech.stvapp.entity.Election;

import java.util.List;
import java.util.Optional;

public interface VoterDao {
    Optional<Integer> getVoterIdByUserId(int userId);
    List<Election> getElections(int voterId);
}
