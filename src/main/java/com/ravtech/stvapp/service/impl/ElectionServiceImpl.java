package com.ravtech.stvapp.service.impl;

import com.ravtech.stvapp.dao.ElectionDao;
import com.ravtech.stvapp.entity.Election;
import com.ravtech.stvapp.service.ElectionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectionServiceImpl implements ElectionService {

    private final ElectionDao electionDAO;

    @Autowired
    public ElectionServiceImpl(ElectionDao electionDAO) {
        this.electionDAO = electionDAO;
    }

    @Override
    @Transactional
    public Election createElection(Election election) {

        // TODO: Add validation

        return electionDAO.save(election);
    }


}
