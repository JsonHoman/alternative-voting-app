package com.ravtech.stvapp.service.impl;

import com.ravtech.stvapp.dao.VoterDAO;
import com.ravtech.stvapp.service.VoterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoterServiceImpl implements VoterService {

    private final VoterDAO voterDAO;

    @Autowired
    public VoterServiceImpl(VoterDAO voterDAO) {
        this.voterDAO = voterDAO;
    }

    @Override
    @Transactional
    public int getVoterIdByUsername(String username) {

        // TODO: Add validation

        return voterDAO.getVoterByUsername(username);
    }
}
