package ru.itis.calculator.operations;

/**
 * @author Vitaly Chekushkin
 */
public class PlusOperation implements Operation {
  public Number apply(Number number1, Number number2) {
    return number1.doubleValue() + number2.doubleValue();
  }

  public String getOperator() {
    return "+";
  }
}
