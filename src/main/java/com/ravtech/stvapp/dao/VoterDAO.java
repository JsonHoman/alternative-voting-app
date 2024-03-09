package com.ravtech.stvapp.dao;

import com.ravtech.stvapp.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterDAO extends JpaRepository<Voter, Integer> {
    Voter findByUser_Username(String username);
}
