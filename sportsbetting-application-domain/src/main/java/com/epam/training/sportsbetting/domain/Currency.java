package com.epam.training.sportsbetting.domain;

public enum Currency {
    HUF,
    EUR,
    USD;

    public static Currency transformStringToCurrency(String name) {
        Currency result = null;
        for (Currency currency : Currency.values()) {
            if (currency.name().equals(name)) {
                result = currency;
                break;
            }
        }
        return result;
    }
}

