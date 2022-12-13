package com.example.equation.controllers;

import com.example.equation.api.ApiResponse;
import com.example.equation.api.CoefficientsRequest;
import com.example.equation.api.RootResponse;
import com.example.equation.exception.UserInputParameterException;
import com.example.equation.service.EquationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EquationController {

    private final EquationService equationService;

    @Autowired
    public EquationController(EquationService equationService) {
        this.equationService = equationService;
    }

    @GetMapping("/equation")
    public ResponseEntity<ApiResponse<RootResponse>> equationHandle(@RequestBody CoefficientsRequest coefficients)
            throws UserInputParameterException {
        ApiResponse<RootResponse> response = new ApiResponse<>();
        response.setDebugMessage("successful request");
        response.setStatus(HttpStatus.OK);
        response.setData(equationService.calculateEquation(coefficients.getA(),
                coefficients.getB(), coefficients.getC()));
        return ResponseEntity.ok(response);
    }
}
