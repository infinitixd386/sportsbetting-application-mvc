package com.epam.training.sportsbetting.service;

public class LowBalanceException extends RuntimeException{

    public LowBalanceException(String msg) {
        super(msg);
    }

    public LowBalanceException() {}
}
