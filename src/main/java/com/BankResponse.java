package com;

import java.math.BigDecimal;

public class BankResponse {
    private BigDecimal quota;
    private BigDecimal rate;
    private Integer term;
    private BigDecimal quotaWithAccount;
    private BigDecimal rateWithAcoount;
    private BigDecimal termWithAccount;

    public BankResponse() {
    }
    public BankResponse(BigDecimal quota, BigDecimal rate, Integer term, BigDecimal quotaWithAccount, BigDecimal rateWithAcoount, BigDecimal termWithAccount) {
        this.quota = quota;
        this.rate = rate;
        this.term = term;
        this.quotaWithAccount = quotaWithAccount;
        this.rateWithAcoount = rateWithAcoount;
        this.termWithAccount = termWithAccount;
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getQuotaWithAccount() {
        return quotaWithAccount;
    }

    public void setQuotaWithAccount(BigDecimal quotaWithAccount) {
        this.quotaWithAccount = quotaWithAccount;
    }

    public BigDecimal getRateWithAcoount() {
        return rateWithAcoount;
    }

    public void setRateWithAcoount(BigDecimal rateWithAcoount) {
        this.rateWithAcoount = rateWithAcoount;
    }

    public BigDecimal getTermWithAccount() {
        return termWithAccount;
    }

    public void setTermWithAccount(BigDecimal termWithAccount) {
        this.termWithAccount = termWithAccount;
    }
}
