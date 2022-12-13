package com.example.equation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "root_equation")
public class RootEquation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double x;

    @ManyToOne
    @JoinColumn(name = "coefficients_id", columnDefinition = "BIGINT", nullable = false)
    private Coefficients coefficients;
}
