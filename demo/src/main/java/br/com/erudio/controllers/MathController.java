package br.com.erudio.controllers;


import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.services.MathServices;
import br.com.erudio.utilsFunctions.DoubleConverter;
import br.com.erudio.utilsFunctions.NumberVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    private MathServices math;

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo)throws UnsupportedMathOperationException{

        return math.sum(DoubleConverter.convertToDouble(numberOne), DoubleConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("subtraction/{numberOne}/{numberTwo}")
    public Double substraction (@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException{
        return math.substraction(DoubleConverter.convertToDouble(numberOne), DoubleConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException{
        return math.multiplication(DoubleConverter.convertToDouble(numberOne), DoubleConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException{

        return math.division(DoubleConverter.convertToDouble(numberOne), DoubleConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException{
        return math.mean(DoubleConverter.convertToDouble(numberOne), DoubleConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("squareroot/{number}")
    public Double squareRoot(@PathVariable("number") String number) throws UnsupportedMathOperationException{
        return math.squareRoot(DoubleConverter.convertToDouble(number));
    }



}
