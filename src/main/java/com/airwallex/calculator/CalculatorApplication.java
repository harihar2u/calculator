package com.airwallex.calculator;

import com.airwallex.calculator.exceptions.ProcessingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Read input from console and process.
 * <p>
 * The System.out can be replace with logger
 **/
public class CalculatorApplication {
    public static void main(String[] args) throws IOException {
        System.out.println("######## Console calculator application started ###########");
        //Read input data from console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        //Perform operation
        Calculator calculator = new Calculator();
        while (!"".equals(input)) {
            try {
                calculator.perform(input);
            } catch (ProcessingException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(calculator.getStackValuesAsString());
                if (calculator.getAdditionErrorInfo() != null) {
                    System.out.println(calculator.getAdditionErrorInfo());
                }
            }
            input = br.readLine();
        }
    }
}
