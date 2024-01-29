package com.ravtech.stvapp.service.impl;

import com.ravtech.stvapp.dao.ElectionDAO;
import com.ravtech.stvapp.entity.Election;
import com.ravtech.stvapp.service.ElectionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectionServiceImpl implements ElectionService {

    private final ElectionDAO electionDAO;

    @Autowired
    public ElectionServiceImpl(ElectionDAO electionDAO) {
        this.electionDAO = electionDAO;
    }

    @Override
    @Transactional
    public Election createElection(Election election) {

        // TODO: Add validation

        return electionDAO.save(election);
    }
}
