package com.airwallex.calculator.operations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class used to store the operation in list for undo operation
 */

public class UndoOperation {
    private List<BigDecimal> undoOperationData;

    private UndoOperation() {
        undoOperationData = new ArrayList<>();
    }

    public UndoOperation(BigDecimal value) {
        this();
        undoOperationData.add(value);
    }

    public UndoOperation(BigDecimal firstValue, BigDecimal secondValue) {
        this(firstValue);
        undoOperationData.add(secondValue);
    }

    public List<BigDecimal> getUndoOperationData() {
        return Collections.unmodifiableList(undoOperationData);
    }
}