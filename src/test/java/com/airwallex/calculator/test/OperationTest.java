package com.airwallex.calculator.test;

import com.airwallex.calculator.Calculator;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class OperationTest {

    @Test
    public void testCalculatorOperation() {
        Calculator calculator = new Calculator();
        assertEquals("Operation value not valid", new BigDecimal(7.0), calculator.perform("5 2 +").setScale(0, BigDecimal.ROUND_DOWN));
        assertEquals("Operation value not valid", new BigDecimal(3.0), calculator.perform("5 2 -").setScale(0, BigDecimal.ROUND_DOWN));
        assertEquals("Operation value not valid", new BigDecimal(10.0), calculator.perform("5 2 *").setScale(0, BigDecimal.ROUND_DOWN));
        assertEquals("Operation value not valid", new BigDecimal(2.5), calculator.perform("5 2 /").setScale(1, BigDecimal.ROUND_DOWN));
        assertEquals("Operation value not valid", new BigDecimal(3.0), calculator.perform("9 sqrt").setScale(0, BigDecimal.ROUND_DOWN));
        assertEquals("Operation value not valid", null, calculator.perform("5 2 clear"));
    }
}
