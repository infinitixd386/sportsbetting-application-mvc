package com.epam.training.sportsbetting.domain;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Player extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal balance;
    @Enumerated
    private Currency currency;
    @Version
    private long version;

    @SuppressWarnings("checkstyle:ParameterNumber")
    public Player(String email, String password, String name, BigDecimal balance, Currency currency) {
        super(email, password);
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    public Player() {
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
