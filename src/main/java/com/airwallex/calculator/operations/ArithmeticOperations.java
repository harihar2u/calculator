package com.airwallex.calculator.operations;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Class support the arithmetic operation supported
 **/
public class ArithmeticOperations {
    public static final Operation ADD = stack -> stack.push(stack.pop().add(stack.pop()));
    public static final Operation SUBTRACT = (stack -> {
        BigDecimal lastOperand = stack.pop();
        stack.push(stack.pop().subtract(lastOperand));
    });
    public static final Operation MULTIPLY = stack -> stack.push(stack.pop().multiply(stack.pop()));
    public static final Operation DIVIDE = (stack -> {
        BigDecimal lastOperand = stack.pop();
        stack.push(stack.pop().divide(lastOperand, MathContext.DECIMAL64));
    });
    public static final Operation CLEAR = stack -> stack.clear();
    public static final Operation SQRT = stack -> stack.push(BigDecimal.valueOf(Math.sqrt(stack.pop().doubleValue())).setScale(15, 1));
}
