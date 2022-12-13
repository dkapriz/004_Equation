package com.example.equation.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoefficientsRequest {
    private Double a;
    private Double b;
    private Double c;
}
