package ru.itis.calculator;

import org.springframework.stereotype.Component;
import ru.itis.calculator.calc.Calculator;
import ru.itis.calculator.calc.exceptions.InvalidFormatException;

/**
 * @author Vitaly Chekushkin
 */

@Component
public class StringExpressionCalculatorAdapter {
  private final Calculator calculator;

  public StringExpressionCalculatorAdapter(Calculator calculator) {
    this.calculator = calculator;
  }

  public Double calculate(String expression) {
    try {
      String[] substrings = expression.split(" +");
      Double number1 = Double.valueOf(substrings[0]);
      Double number2 = Double.valueOf(substrings[1]);
      String operation = substrings[2];
      return calculator.calculate(number1, number2, operation);
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      throw new InvalidFormatException("Invalid format of expression", e);
    }
  }
}
