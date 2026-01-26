package br.com.erudio.utilsFunctions;

import br.com.erudio.exceptions.UnsupportedMathOperationException;

public class NumberVerification {

    public static boolean isNumeric(String strNumber) throws UnsupportedMathOperationException {
        if (strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value");
        String number = strNumber.replace(",", ".");
        //return number.matches("[-+]?[0-9]\\.?[0-9]+");
        return number.matches("[-+]?[0-9]+(\\.[0-9]+)?");
    }

}
