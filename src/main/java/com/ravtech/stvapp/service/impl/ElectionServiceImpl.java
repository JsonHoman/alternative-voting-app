package com.ravtech.stvapp.service.impl;

import com.ravtech.stvapp.dao.ElectionDao;
import com.ravtech.stvapp.dao.UserDao;
import com.ravtech.stvapp.dao.VoterDao;
import com.ravtech.stvapp.entity.Election;
import com.ravtech.stvapp.service.ElectionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionServiceImpl implements ElectionService {

    private final ElectionDao electionDAO;
    private final UserDao userDao;
    private final VoterDao voterDao;

    @Autowired
    public ElectionServiceImpl(ElectionDao electionDAO, UserDao userDao, VoterDao voterDao) {
        this.electionDAO = electionDAO;
        this.userDao = userDao;
        this.voterDao = voterDao;
    }

    @Override
    @Transactional
    public Election createElection(Election election) {

        // TODO: Add validation

        return electionDAO.save(election);
    }

    @Override
    @Transactional
    public List<Election> getVoterElections(String username) {

        Optional<Integer> userIdOptional = userDao.getUserIdByUsername(username);

        // add details to orElseThrow params
        int userId = userIdOptional.orElseThrow();

        Optional<Integer> voterIdOptional = voterDao.getVoterIdByUserId(userId);

        // add details to orElseThrow params
        int voterId = voterIdOptional.orElseThrow();

        return electionDao.getVoterElections(voterId);
    }
}
