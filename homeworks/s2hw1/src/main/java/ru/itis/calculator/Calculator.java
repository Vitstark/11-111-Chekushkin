package ru.itis.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import ru.itis.calculator.operations.Operation;

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

  @Override
  public String toString() {
    return "Calculator{" +
           "operations=" + operations +
           '}';
  }
}
