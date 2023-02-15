package ru.itis.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itis.calculator.Calculator;
import ru.itis.calculator.operations.MinusOperation;
import ru.itis.calculator.operations.Operation;
import ru.itis.calculator.operations.PlusOperation;

/**
 * @author Vitaly Chekushkin
 */
@Configuration
public class CalculatorConfiguration {
  @Bean
  public Calculator calculator(Operation... operations) {
    return new Calculator(operations);
  }

  @Bean
  public Operation plusOperator() {
    return new PlusOperation();
  }

  @Bean
  public Operation minusOperator() {
    return new MinusOperation();
  }
}
