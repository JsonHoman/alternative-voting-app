package com.ravtech.stvapp.dao;

import java.util.Optional;

public interface VoterDao {
    Optional<Integer> getVoterIdByUserId(Integer userId);
}
