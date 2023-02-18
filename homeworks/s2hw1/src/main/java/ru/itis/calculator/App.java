package ru.itis.calculator;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.calculator.calc.Calculator;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.itis.calculator.config");
        Calculator calculator = context.getBean(Calculator.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String expression = scanner.nextLine();
            Double result = calculator.calculate(expression);
            System.out.println(result);
        }
    }
}
