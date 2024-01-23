package com.ravtech.stvapp.dao;

import com.ravtech.stvapp.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionDAO extends JpaRepository<Election, Integer> {
}
