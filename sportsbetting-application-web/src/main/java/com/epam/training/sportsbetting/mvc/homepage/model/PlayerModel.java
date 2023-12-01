package com.epam.training.sportsbetting.mvc.homepage.model;

import com.epam.training.sportsbetting.mvc.login.model.LoginModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PlayerModel extends LoginModel {

    @NotNull
    private String name;
    @NotNull(message = "Please enter your balance.")
    @Min(value = 0L, message = "The value must be positive")
    private BigDecimal balance;
    @NotNull
    private String currency;
    private long version;

    public PlayerModel(String email, String password, String name, BigDecimal balance, String currency) {
        super(email, password);
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    public PlayerModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
