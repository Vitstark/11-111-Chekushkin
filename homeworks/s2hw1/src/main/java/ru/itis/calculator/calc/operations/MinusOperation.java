package ru.itis.calculator.calc.operations;

/**
 * @author Vitaly Chekushkin
 */
public class MinusOperation implements Operation {
  public Double apply(Double number1, Double number2) {
    return number1 - number2;
  }

  public String getOperator() {
    return "-";
  }
}
