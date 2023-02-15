package ru.itis.calculator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.itis.calculator");
        Calculator calculator = context.getBean(Calculator.class);
        System.out.println(calculator);
    }
}
