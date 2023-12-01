package com.epam.training.sportsbetting.view;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleInputAndOutput implements ConsoleIO {

    private Scanner keyboard = new Scanner(System.in);

    @Override
    public void println(String consoleOutput) {
        System.out.println(consoleOutput);
    }

    @Override
    public void printf(String consoleOutput) {
        System.out.printf(consoleOutput);
    }

    @Override
    public String nextLine() {
        return keyboard.next();
    }

    @Override
    public BigDecimal nextBigDecimal() {
        return keyboard.nextBigDecimal();
    }

}
