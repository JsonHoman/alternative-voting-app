package com.ravtech.stvapp.dao.impl;

import com.ravtech.stvapp.dao.ElectionDAO;
import com.ravtech.stvapp.entity.Election;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElectionDAOImpl implements ElectionDAO {
    private final EntityManager entityManager;

    @Autowired
    public ElectionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Election createElection(Election election) {

        entityManager.persist(election);

        return election;
    }
}
