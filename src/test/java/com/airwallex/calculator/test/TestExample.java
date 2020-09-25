package com.airwallex.calculator.test;

import com.airwallex.calculator.Calculator;
import com.airwallex.calculator.exceptions.ProcessingException;
import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

public class TestExample {
    @Test
    public void test1() {
        Calculator calculator = new Calculator();
        try {
            calculator.perform("5 2");

            assertEquals("Stack length should be 2", 2, calculator.getStackValues().size());
            assertEquals("Stack value is not valid", "5 2",  calculator.getStackValuesAsString());
        } catch (ProcessingException e) {
            fail("Operation failed");
        }
    }

    @Test
    public void test2() {
        try {
            Calculator calculator = new Calculator();
            //1st command
            calculator.perform("2 sqrt");
            assertEquals("Stack should have one number", 1, calculator.getStackValues().size());
            assertEquals("Sqrt of 2 is not valid","1.4142135623", calculator.getStackValuesAsString());
            //2nd command
            calculator.perform("clear 9 sqrt");
            assertEquals("Stack size should be 1", 1, calculator.getStackValues().size());
            assertEquals("Sqrt of 3 is not valid", "3", calculator.getStackValuesAsString());
        } catch (ProcessingException e) {
            fail("Operation failed");
        }
    }

    @Test
    public void test3() {
        try {
            Calculator calculator = new Calculator();
            //1st command
            calculator.perform("5 2 -");
            assertEquals("Stack length should be 1", 1, calculator.getStackValues().size());
            assertEquals("Stack value should be 3", "3", calculator.getStackValuesAsString());
             //2nd command
            calculator.perform("3 -");
            assertEquals("Stack length should be 1", 1, calculator.getStackValues().size());
            assertEquals("stack value should be 0", "0", calculator.getStackValuesAsString());
            //3rd command
            calculator.perform("clear");
            assertEquals("Stack should be empty", 0, calculator.getStackValues().size());
        } catch (ProcessingException e) {
            fail("Operation failed");
        }
    }

    @Test
    public void test4() {
        try {
            Calculator calculator = new Calculator();
            //1st command
            calculator.perform("5 4 3 2");
            assertEquals("Stack length should be 4", 4, calculator.getStackValues().size());
            assertEquals("Stack value should be 5 4 3 2", "5 4 3 2", calculator.getStackValuesAsString());
            //2nd command
            calculator.perform("undo undo *");
            assertEquals("Stack size should be 1", 1, calculator.getStackValues().size());
            assertEquals("Stack value should be 20", "20", calculator.getStackValuesAsString());
            //3rd command
            calculator.perform("5 *");
            assertEquals("Stack size should be 1", 1, calculator.getStackValues().size());
            assertEquals("Stack value should be 100", "100", calculator.getStackValuesAsString());
            //4th command
            calculator.perform("undo");;
            assertEquals("Stack size should be 2", 2, calculator.getStackValues().size());
            assertEquals("Stack value should be 20 5", "20 5", calculator.getStackValuesAsString());
        } catch (ProcessingException e) {
            fail("Operation failed");
        }
    }

    @Test
    public void test5() {
        try {
            Calculator calculator = new Calculator();
             //1st command
            calculator.perform("7 12 2 /");
            assertEquals("Stack length should be 2", 2, calculator.getStackValues().size());
            assertEquals("Stack value should be 7 6", "7 6", calculator.getStackValuesAsString());
             //2nd command
            calculator.perform("*");
            assertEquals("Stack size should be one", 1, calculator.getStackValues().size());
            assertEquals("Stack value should be 42", "42", calculator.getStackValuesAsString());
            //3rd command
            calculator.perform("4 /");
            assertEquals("Stack size should have one", 1, calculator.getStackValues().size());
            assertEquals("Stack value should be 10.5", "10.5", calculator.getStackValuesAsString());
        } catch (ProcessingException e) {
            fail("Operation failed");
        }
    }

    @Test
    public void test6() {
        try {
            Calculator calculator = new Calculator();
             //1st command
            calculator.perform("1 2 3 4 5");
            assertEquals("Stack size should be 5", 5, calculator.getStackValues().size());
            assertEquals("Stack value should be 1 2 3 4 5", "1 2 3 4 5", calculator.getStackValuesAsString());
            //2nd command
            calculator.perform("*");
            assertEquals("Stack size should be 4", 4, calculator.getStackValues().size());
            assertEquals("Stack value should be 1 2 3 20", "1 2 3 20", calculator.getStackValuesAsString());
            //3rd command
            calculator.perform("clear 3 4 -");
            assertEquals("Stack size should be 1", 1, calculator.getStackValues().size());
            assertEquals("Stack value should be -1", "-1", calculator.getStackValuesAsString());
        } catch (ProcessingException e) {
            fail("Operation failed");
        }
    }

    @Test
    public void test7() {
        try {
            Calculator calculator = new Calculator();
             //1st command
            calculator.perform("1 2 3 4 5");
            assertEquals("Stack size should be 5", 5, calculator.getStackValues().size());
            assertEquals("Stack value should be 1 2 3 4 5", "1 2 3 4 5", calculator.getStackValuesAsString());
            //2nd command
            calculator.perform("* * * *");
            assertEquals("Stack size should be 1", 1, calculator.getStackValues().size());
            assertEquals("Stack value should be 120", "120", calculator.getStackValuesAsString());
        } catch (ProcessingException e) {
            fail("Operation failed");
        }
    }

    @Test
    public void test8() {
        Calculator calculator = new Calculator();
        try {
            calculator.perform("1 2 3 * 5 + * * 6 5 ");
            fail("Operation should fail...");
        } catch (ProcessingException e) {
            assertTrue(e.getMessage().contains("operator *(position: 8): insufficient parameters"));
            assertEquals("Stack value should be 11", "11",calculator.getStackValuesAsString());
        }
    }

}
