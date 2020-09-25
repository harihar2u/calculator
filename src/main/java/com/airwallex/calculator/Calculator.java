package com.airwallex.calculator;

import com.airwallex.calculator.exceptions.ProcessingException;
import com.airwallex.calculator.operations.ArithmeticOperationWithUndo;
import com.airwallex.calculator.operations.CalculatorOperation;
import com.airwallex.calculator.operations.UndoOperation;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Calculator {
    /**
     * stack to store data and operation results
     */
    private Stack<BigDecimal> stack = new Stack<>();
    /**
     * UNDO stack where operation of undo data stored
     */
    private Stack<UndoOperation> undoStack = new Stack<>();
    /**
     * Used for addition info error in input
     **/
    private String additionalInfo;

    /**
     * Parse the input and perform the operation
     * @param input input values
     * @throws ProcessingException in case of failure
     */
    public BigDecimal perform(String input) throws ProcessingException {
        process(input.split(" "));
        return this.stack.size() > 0 ? this.stack.peek() : null;
    }

    /**
     * @return converted stack value in string format
     */
    public String getStackValuesAsString() {
        List<BigDecimal> values = getStackValues();
        return values.stream()
                .map(d-> d.setScale(10, BigDecimal.ROUND_DOWN))
                .map(s -> String.format("%.10f", s).replaceAll("\\.*0*$", ""))
                .collect(Collectors.joining(" "));
    }

    /**
     * return additional error info
     */
    public String getAdditionErrorInfo() {
       return additionalInfo;
    }

    /**
     * Return the unmodifiable values of stack
     * @return list of stack values.
     */
    public List<BigDecimal> getStackValues() {
        return Collections.unmodifiableList(new ArrayList<>(stack));
    }

    /**
     * Parse the input string and perform operation
     * @param tokens  input string for processing
     * @throws ProcessingException failure in operation
     */
    private void process(String... tokens) throws ProcessingException {
        int position = 0;
        for (String token : tokens) {
            try {
                position++;
                processInput(token).process(stack, undoStack);
            }
            catch(ProcessingException exception) {
                if(exception.getOperator()!=null && exception.getMessage().contains("insufficient parameters")) {
                    String value = IntStream.rangeClosed(position, tokens.length-1).mapToObj(i -> tokens[i]).collect(Collectors.joining(" "));
                    additionalInfo = String.format("(the value %s  were not pushed on to the stack due to the previous error)", value);
                }
                throw new ProcessingException(exception.getOperator(), position, exception.getMessage());
            }
        }
    }

    /**
     * Find the operation related to the word.
     * @param input Input String
     * @throws ProcessingException failure in operation
     */
    private CalculatorOperation processInput(String input) throws ProcessingException {
        if (ArithmeticOperationWithUndo.SUPPORTED_OPERATIONS.get(input) != null) {
            return ArithmeticOperationWithUndo.SUPPORTED_OPERATIONS.get(input);
        }
        try {
            BigDecimal value = new BigDecimal(input);
            return ((dataStack, undoStack) -> ArithmeticOperationWithUndo.pushData(dataStack, value));
        } catch (NumberFormatException e) {
            throw new ProcessingException(input, "invalid input");
        }
    }
}


