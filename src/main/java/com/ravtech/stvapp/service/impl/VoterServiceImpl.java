package com.ravtech.stvapp.service.impl;

import com.ravtech.stvapp.dao.VoterDao;
import com.ravtech.stvapp.service.VoterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoterServiceImpl implements VoterService {

    private final VoterDao voterDAO;

    @Autowired
    public VoterServiceImpl(VoterDao voterDAO) {
        this.voterDAO = voterDAO;
    }

    @Override
    @Transactional
    public int getVoterIdByUsername(String username) {

        // TODO: Add validation

        return voterDAO.getVoterByUsername(username);
    }
}
