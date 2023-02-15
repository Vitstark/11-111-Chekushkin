package ru.itis.calculator.operations;

/**
 * @author Vitaly Chekushkin
 */
public interface Operation {
  Number apply(Number number1, Number number2);
  String getOperator();
}
