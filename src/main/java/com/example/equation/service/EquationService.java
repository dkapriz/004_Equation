package com.example.equation.service;

import com.example.equation.api.RootResponse;
import com.example.equation.exception.UserInputParameterException;
import com.example.equation.model.Coefficients;
import com.example.equation.model.RootEquation;
import com.example.equation.repositories.CoefficientsRepositories;
import com.example.equation.repositories.RootEquationRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquationService {

    public static final String ERROR_MSG_WRONG_PARAMETER = "Wrong values passed to one or more parameters";
    public static final String ERROR_MSG_ROOT_MISSING = "the equation has no roots";
    private final CoefficientsRepositories coefficientsRepositories;
    private final RootEquationRepositories rootEquationRepositories;

    @Autowired
    public EquationService(CoefficientsRepositories coefficientsRepositories,
                           RootEquationRepositories rootEquationRepositories) {
        this.coefficientsRepositories = coefficientsRepositories;
        this.rootEquationRepositories = rootEquationRepositories;
    }

    public RootResponse calculateEquation(Double a, Double b, Double c) throws
            UserInputParameterException, ArithmeticException {
        checkCoefficients(a, b, c);
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            throw new  ArithmeticException(ERROR_MSG_ROOT_MISSING);
        }
        Coefficients coefficients = saveCoefficients(a, b, c);
        if (discriminant == 0) {
            double x = -b / (2 * a);
            saveRoot(coefficients, x);
            return new RootResponse(x, null);
        }
        double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
        saveRoot(coefficients, x1);
        saveRoot(coefficients, x2);
        return new RootResponse(x1, x2);
    }

    private Coefficients saveCoefficients(Double a, Double b, Double c) {
        Coefficients coefficients = new Coefficients();
        coefficients.setA(a);
        coefficients.setB(b);
        coefficients.setC(c);
        return coefficientsRepositories.save(coefficients);
    }

    private void saveRoot(Coefficients coefficients, Double x) {
        RootEquation rootEquation = new RootEquation();
        rootEquation.setCoefficients(coefficients);
        rootEquation.setX(x);
        rootEquationRepositories.save(rootEquation);
    }

    private void checkCoefficients(Double a, Double b, Double c) throws UserInputParameterException {
        if (a == null || b == null || c == null) {
            throw new UserInputParameterException(ERROR_MSG_WRONG_PARAMETER);
        }
    }
}
