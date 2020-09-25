package com.airwallex.calculator.operations;

import com.airwallex.calculator.exceptions.ProcessingException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class used to perform the operation, store the operation result and add operation for undo operation
 **/
public class ArithmeticOperationWithUndo {
    public static final CalculatorOperation ADD = ((stack, undo) -> {
        checkOperands(stack, "+", 2);
         //get the operand in stack for operation
        Iterator<BigDecimal> iterator = stack.iterator();
        BigDecimal secondValue = iterator.next();
        BigDecimal firstValue = iterator.next();
        //execute operation
        ArithmeticOperations.ADD.execute(stack);
        //add operand in undo stack
        undo.push(new UndoOperation(firstValue, secondValue));
    });
    public static final CalculatorOperation SUBTRACT = ((stack, undo) -> {
        checkOperands(stack, "-", 2);
        //get the operand in stack for operation
        Iterator<BigDecimal> iterator = stack.iterator();
        BigDecimal secondValue = iterator.next();
        BigDecimal firstValue = iterator.next();
        //execute operation
        ArithmeticOperations.SUBTRACT.execute(stack);
        //add operand in undo stack
        undo.push(new UndoOperation(firstValue, secondValue));
    });
    public static final CalculatorOperation MULTIPLY = ((stack, undo) -> {
        Iterator<BigDecimal> iterator = stack.iterator();
        checkOperands(stack, "*", 2);
          //get the operand in stack for operation
        BigDecimal firstValue = iterator.next();
        BigDecimal secondValue =  iterator.next();
        //execute operation
        ArithmeticOperations.MULTIPLY.execute(stack);
        //add operand in undo stack
        undo.push(new UndoOperation(firstValue, secondValue));
    });
    public static final CalculatorOperation DIVIDE = ((stack, undo) -> {
        Iterator<BigDecimal> iterator = stack.iterator();
        checkOperands(stack, "/", 2);
        //get the operand in stack for operation
        BigDecimal secondValue = iterator.next();
        BigDecimal firstValue = iterator.next();
        //execute operation
        ArithmeticOperations.DIVIDE.execute(stack);
        //add operand in undo stack
        undo.push(new UndoOperation(firstValue, secondValue));
    });
    public static final CalculatorOperation CLEAR = ((stack, undo) -> {
        if (stack.size() == 0) {
            throw new ProcessingException("clear");
        }
        //execute operation
        ArithmeticOperations.CLEAR.execute(stack);
        //clear undo stack
        undo.clear();
    });
    public static final CalculatorOperation SQRT = ((stack, undo) -> {
        BigDecimal value = stack.peek();
        //execute operation
        ArithmeticOperations.SQRT.execute(stack);
        //add operand in undo stack
        undo.push(new UndoOperation(value));
    });
    public static final CalculatorOperation UNDO = ((stack, undo) -> {
        if (stack.size() > 0) {
            //remove the last operation result from stack
            stack.pop();
            //copy operand from undo stack to operation stack
            if (undo.size() > 0) {
                undo.pop().getUndoOperationData().forEach(item -> stack.push(item));
            }
        } else {
              throw new ProcessingException("undo","Stack is empty, nothing to undo");
            }
    });

    /**
     * Check minimum operand in stack for operation.
     *
     * @param stack       data structure to store the operand and result
     * @param operation   operation to be performed
     * @param minOperands size of operand fro operation
     * @throws ProcessingException in case of failure
     */
    private static void checkOperands(Stack<BigDecimal> stack, String operation, int minOperands) throws ProcessingException {
        if (stack.size() < minOperands) {
            throw new ProcessingException(operation, "insufficient parameters");
        }
    }

    /**
     * @param stack used to stored operand and result
     * @param value value to be pushed
     */
    public static void pushData(Stack<BigDecimal> stack, BigDecimal value) {
        stack.push(value);
    }

    public static final Map<String, CalculatorOperation> SUPPORTED_OPERATIONS = Stream.of(
            new AbstractMap.SimpleEntry<>("+", ADD),
            new AbstractMap.SimpleEntry<>("-", SUBTRACT),
            new AbstractMap.SimpleEntry<>("*", MULTIPLY),
            new AbstractMap.SimpleEntry<>("/", DIVIDE),
            new AbstractMap.SimpleEntry<>("sqrt", SQRT),
            new AbstractMap.SimpleEntry<>("clear", CLEAR),
            new AbstractMap.SimpleEntry<>("undo", UNDO))
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

}
