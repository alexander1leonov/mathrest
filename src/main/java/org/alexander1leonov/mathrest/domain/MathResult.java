package org.alexander1leonov.mathrest.domain;

public class MathResult {
    private Double[] numbers;
    private Double sum;
    private String errorMessage;

    public Double[] getNumbers() {
        return numbers;
    }

    public void setNumbers(Double[] numbers) {
        this.numbers = numbers;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
