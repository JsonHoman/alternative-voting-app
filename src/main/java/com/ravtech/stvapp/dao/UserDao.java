package com.ravtech.stvapp.dao;

import com.ravtech.stvapp.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findByUsername(String username);

    Optional<Integer> getUserIdByUsername(String username);
}
