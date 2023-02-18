package ru.itis.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itis.calculator.calc.Calculator;
import ru.itis.calculator.calc.operations.*;

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

  @Bean
  public Operation multiplicationOperation() {
    return new MultiplicationOperation();
  }

  @Bean
  public Operation divisionOperation() {
    return new DivisionOperation();
  }
}
