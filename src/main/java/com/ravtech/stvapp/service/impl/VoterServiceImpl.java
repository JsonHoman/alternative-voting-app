package com.ravtech.stvapp.service.impl;

import com.ravtech.stvapp.dao.UserDao;
import com.ravtech.stvapp.dao.VoterDao;
import com.ravtech.stvapp.entity.Election;
import com.ravtech.stvapp.service.VoterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VoterServiceImpl implements VoterService {

    private final VoterDao voterDao;
    private final UserDao userDao;

    @Autowired
    public VoterServiceImpl(VoterDao voterDao, UserDao userDao) {
        this.voterDao = voterDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public int getVoterIdByUsername(String username) {

        // TODO: Add validation

        return voterDao.getVoterByUsername(username);
    }

    @Override
    @Transactional
    public List<Election> getElections(String username) {

        Optional<Integer> userIdOptional = userDao.getUserIdByUsername(username);

        if (userIdOptional.isEmpty()) {
            return Collections.emptyList();
        }

        int userId = userIdOptional.get();
        Optional<Integer> voterIdOptional = voterDao.getVoterIdByUserId(userId);
        if (voterIdOptional.isEmpty()) {
            return Collections.emptyList();
        }

        int voterId = voterIdOptional.get();
        return voterDao.getElections(voterId);
    }
}
