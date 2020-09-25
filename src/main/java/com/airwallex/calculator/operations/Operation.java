package com.airwallex.calculator.operations;

import com.airwallex.calculator.exceptions.ProcessingException;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.Stack;

@FunctionalInterface
public interface Operation {
    /**
     * @param stack stack to perform operation and store the operation result
     **/
    void execute(Stack<BigDecimal> stack) throws ProcessingException;
}
