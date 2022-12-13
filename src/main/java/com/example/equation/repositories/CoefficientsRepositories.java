package com.example.equation.repositories;

import com.example.equation.model.Coefficients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoefficientsRepositories extends JpaRepository<Coefficients, Long> {
}
