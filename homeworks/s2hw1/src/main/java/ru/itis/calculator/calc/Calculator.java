package ru.itis.calculator.calc;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import ru.itis.calculator.calc.exceptions.InvalidFormatException;
import ru.itis.calculator.calc.operations.Operation;

/**
 * @author Vitaly Chekushkin
 */
public class Calculator {
  private final Map<String, Operation> operations;
  public Calculator(Operation ... operations) {
    this.operations = Arrays.stream(operations)
        .collect(Collectors.toMap(
            Operation::getOperator, operation -> operation));
  }

  public Double calculate(String expression) {
    try {
      String[] substrings = expression.split(" +");
      Double number1 = Double.parseDouble(substrings[0]);
      Double number2 = Double.parseDouble(substrings[1]);
      Operation operation = operations.get(substrings[2]);

      return operation.apply(number1, number2);
    } catch (NullPointerException e) {
      throw new UnsupportedOperationException("This operator is not support", e);
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      throw new InvalidFormatException("Invalid format of expression", e);
    }
  }

  @Override
  public String toString() {
    return "Calculator{" +
           "operations=" + operations +
           '}';
  }
}
