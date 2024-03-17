package com.ravtech.stvapp.dao.impl;

import com.ravtech.stvapp.controller.exception.QueryExecutionException;
import com.ravtech.stvapp.dao.VoterDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class VoterDaoImpl implements VoterDao {

    private final EntityManager entityManager;

    @Autowired
    public VoterDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Integer> getVoterIdByUserId(Integer userId) throws IllegalArgumentException {

        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        String queryStr = "SELECT v.id FROM Voter v WHERE v.user_id = :userIdData";
        TypedQuery<Integer> query = entityManager.createQuery(queryStr, Integer.class)
                .setParameter("userIdData", userId);

        return getVoterId(userId, query);
    }

    private static Optional<Integer> getVoterId(Integer userId, TypedQuery<Integer> query) {

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
}
