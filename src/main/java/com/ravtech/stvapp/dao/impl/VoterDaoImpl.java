package com.ravtech.stvapp.dao.impl;

import com.ravtech.stvapp.controller.exception.QueryExecutionException;
import com.ravtech.stvapp.dao.VoterDao;
import com.ravtech.stvapp.entity.Election;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VoterDaoImpl implements VoterDao {

    private final EntityManager entityManager;

    @Autowired
    public VoterDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Integer> getVoterIdByUserId(int userId) {

        String queryStr = "SELECT v.id FROM Voter v WHERE v.user_id = :userIdData";
        TypedQuery<Integer> query = entityManager.createQuery(queryStr, Integer.class)
                .setParameter("userIdData", userId);

        Optional<Integer> voterIdOptional;
        try {
            voterIdOptional = Optional.ofNullable(query.getSingleResult());

        } catch (NoResultException ex) {
            voterIdOptional = Optional.empty();

        } catch (NonUniqueResultException ex) {
            throw new IllegalStateException("Multiple voterIds found for userId: " + userId, ex);

        } catch (Exception ex) {
            throw new QueryExecutionException("Error executing query: " + ex.getMessage(), ex);

        }

        return voterIdOptional;
    }

    @Override
    public List<Election> getElections(int voterId) {

        String queryStr = "SELECT e FROM Voter v JOIN v.elections e WHERE v.id = :voterIdData";
        TypedQuery<Election> query = entityManager.createQuery(queryStr, Election.class)
                .setParameter("voterIdData", voterId);

        List<Election> elections;
        try {
            elections = query.getResultList();

        } catch (Exception ex) {
            throw new QueryExecutionException("Error executing query: " + ex.getMessage(), ex);

        }

        return elections;
    }
}
