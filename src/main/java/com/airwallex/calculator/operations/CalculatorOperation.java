package com.airwallex.calculator.operations;
import com.airwallex.calculator.exceptions.ProcessingException;
import java.math.BigDecimal;
import java.util.Stack;

@FunctionalInterface
public interface CalculatorOperation {
    /**
     * @param stack data structure to add operand and result
     * @param undoStack data structure to store the operatio for undo
     * @throws ProcessingException in case of any error
     **/
    void process(Stack<BigDecimal> stack, Stack<UndoOperation> undoStack) throws ProcessingException;
}
