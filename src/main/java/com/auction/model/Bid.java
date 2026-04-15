package com.auction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Bid {
    private final String bidderName;
    private final BigDecimal amount;
    private final LocalDateTime bidTime;

    public Bid(String bidderName, BigDecimal amount, LocalDateTime bidTime) {
        this.bidderName = bidderName;
        this.amount = amount;
        this.bidTime = bidTime;
    }

    public String getBidderName() {
        return bidderName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bid bid)) return false;
        return Objects.equals(bidderName, bid.bidderName)
                && Objects.equals(amount, bid.amount)
                && Objects.equals(bidTime, bid.bidTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidderName, amount, bidTime);
    }

    @Override
    public String toString() {
        return "Bid{" +
                "bidderName='" + bidderName + '\'' +
                ", amount=" + amount +
                ", bidTime=" + bidTime +
                '}';
    }
}
