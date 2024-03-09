package com.ravtech.stvapp.dao.impl;

import com.ravtech.stvapp.controller.exception.QueryExecutionException;
import com.ravtech.stvapp.dao.UserDAO;
import com.ravtech.stvapp.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDAO  {

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public Optional<User> findByUsername(String username) throws IllegalArgumentException {

        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        String queryStr = "from User where username=:nameData and enabled=true";
        TypedQuery<User> query = entityManager.createQuery(queryStr, User.class);
        query.setParameter("nameData", username);

        return getUser(username, query);
    }

    private static Optional<User> getUser(String username, TypedQuery<User> query) {
        Optional<User> userOptional;
        try {
            userOptional = Optional.ofNullable(query.getSingleResult());
            // TODO: Ensure that user.username is enforced to be unique upon creation

        } catch (NoResultException ex) {
            userOptional = Optional.empty();

        } catch (NonUniqueResultException ex) {
            throw new IllegalStateException("Multiple users found for username: " + username, ex);

        } catch (Exception ex) {
            throw new QueryExecutionException("Error executing query: " + ex.getMessage(), ex);

        }
        return userOptional;
    }
}
