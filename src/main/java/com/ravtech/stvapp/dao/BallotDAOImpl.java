package com.ravtech.stvapp.dao;

import com.ravtech.stvapp.entity.Ballot;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BallotDAOImpl implements BallotDAO {
    private EntityManager entityManager;

    @Autowired
    public BallotDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void createBallot(Ballot ballot) {
        entityManager.persist(ballot);
    }
}
