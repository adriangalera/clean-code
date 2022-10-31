package no.nerdschool.uglycode;

import java.util.Objects;

public class Operation {

    private final int firstOperand;
    private final int secondOperand;
    private final String operator;

    public Operation(int firstOperand, int secondOperand, String operator) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    public int execute() {
        if (operator.equals("+")) {
            return getFirstOperand() + getSecondOperand();
        }

        if (operator.equals("-")) {
            return -getSecondOperand() - (-getFirstOperand());
        }

        if (operator.equals("*")) {
            return getFirstOperand() * getSecondOperand();
        } else {
            return getSecondOperand() == 0 ? 0 :
                (int) ((1 / (double) getSecondOperand()) * getFirstOperand());
        }
    }

    public int getFirstOperand() {
        return firstOperand;
    }

    public int getSecondOperand() {
        return secondOperand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Operation operation = (Operation) o;
        return firstOperand == operation.firstOperand && secondOperand == operation.secondOperand && operator.equals(
            operation.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstOperand, secondOperand, operator);
    }
}
