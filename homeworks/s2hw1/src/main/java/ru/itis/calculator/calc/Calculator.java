package ru.itis.calculator.calc;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import ru.itis.calculator.calc.exceptions.InvalidFormatException;
import ru.itis.calculator.calc.operations.Operation;

/**
 * @author Vitaly Chekushkin
 */

@Component
public class Calculator {
  private final Map<String, Operation> operations;
  public Calculator(Operation ... operations) {
    this.operations = Arrays.stream(operations)
        .collect(Collectors.toMap(
            Operation::getOperator, operation -> operation));
  }

  public Double calculate(Double a, Double b, String op) {
    try {
      Operation operation = operations.get(op);
      return operation.apply(a, b);
    } catch (NullPointerException e) {
      throw new UnsupportedOperationException("This operator is not support", e);
    }
  }

  @Override
  public String toString() {
    return "Calculator{" +
           "operations=" + operations +
           '}';
  }
}
