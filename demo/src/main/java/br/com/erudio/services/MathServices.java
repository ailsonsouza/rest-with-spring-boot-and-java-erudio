package br.com.erudio.services;

import br.com.erudio.exceptions.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class MathServices {

    public Double sum(double numberOne, double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double substraction (double numberOne, double numberTwo) {
        return numberOne - numberTwo;
    }

    public Double multiplication(double numberOne, double numberTwo){
        return numberOne * numberTwo;
    }

    public Double division(double numberOne, double numberTwo){
        if (numberTwo == 0) throw new UnsupportedMathOperationException("Impossible to divide by zero");

        return numberOne / numberTwo;
    }

    public Double mean(double numberOne, double numberTwo){
        return (numberOne +numberTwo) / 2;
    }

    public Double squareRoot(double number){
        return Math.sqrt(number);
    }
}
