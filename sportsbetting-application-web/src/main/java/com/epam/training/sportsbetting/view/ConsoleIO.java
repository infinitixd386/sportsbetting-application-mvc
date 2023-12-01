package com.epam.training.sportsbetting.view;

import java.math.BigDecimal;

public interface ConsoleIO {
    void println(String consoleOutput);
    void printf(String consoleOutput);
    String nextLine();
    BigDecimal nextBigDecimal();
}
