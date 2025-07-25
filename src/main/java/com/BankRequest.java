package com;

import java.math.BigDecimal;

public class BankRequest {
    private BigDecimal amount;
    private Integer term;
    private BigDecimal rate;

    public BankRequest() {
    }
    public BankRequest(BigDecimal amount, Integer term, BigDecimal rate) {
        this.amount = amount;
        this.term = term;
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
