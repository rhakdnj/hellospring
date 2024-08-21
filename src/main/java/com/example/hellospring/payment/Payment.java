package com.example.hellospring.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Payment {
    private final Long orderId;
    private final String currency;
    private final BigDecimal foreignCurrencyAmount;
    private final BigDecimal exchangeRate;
    private final BigDecimal convertedAmount;
    private final LocalDateTime validUntil;
}
