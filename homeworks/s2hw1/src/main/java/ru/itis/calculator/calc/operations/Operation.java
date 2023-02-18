package ru.itis.calculator.calc.operations;

/**
 * @author Vitaly Chekushkin
 */
public interface Operation {
  Double apply(Double number1, Double number2);
  String getOperator();
}
