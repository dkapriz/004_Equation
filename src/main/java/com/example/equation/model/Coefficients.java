package com.example.equation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Coefficients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double a;
    private Double b;
    private Double c;

    @OneToMany(mappedBy = "coefficients", cascade = CascadeType.ALL)
    List<RootEquation> rootEquationList;
}
