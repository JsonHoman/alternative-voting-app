package com.ravtech.stvapp.dao;

import com.ravtech.stvapp.entity.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> findByUsername(String username);
}
