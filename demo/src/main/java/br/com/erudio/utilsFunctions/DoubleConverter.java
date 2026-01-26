package br.com.erudio.utilsFunctions;

import br.com.erudio.exceptions.UnsupportedMathOperationException;

public class DoubleConverter {

    public static Double convertToDouble(String numberReceived){
        if (numberReceived == null) throw new UnsupportedMathOperationException("Value cannot be null");

        if (NumberVerification.isNumeric(numberReceived)) {
            String number = numberReceived.replace(",", ".");
            return Double.parseDouble(number);
        }
        throw new UnsupportedMathOperationException("Please set a numeric value");
    }
}
