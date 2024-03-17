package com.ravtech.stvapp.dao;

import com.ravtech.stvapp.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionDao extends JpaRepository<Election, Integer> {
}
