package com.example.equation.repositories;

import com.example.equation.model.RootEquation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootEquationRepositories extends JpaRepository<RootEquation, Long> {
}
