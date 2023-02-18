package ru.itis.calculator.calc.operations;

/**
 * @author Vitaly Chekushkin
 */
public class DivisionOperation implements Operation {
  @Override
  public Double apply(Double number1, Double number2) {
    return number1 / number2;
  }

  @Override
  public String getOperator() {
    return "/";
  }
}
