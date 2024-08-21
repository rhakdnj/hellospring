package com.example.hellospring.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

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

	public static Payment createPrepared(
		Long orderId,
		String currency,
		BigDecimal foreignCurrencyAmount,
		BigDecimal exchangeRate,
		LocalDateTime now
	) {
		Objects.requireNonNull(orderId);
		Objects.requireNonNull(currency);
		Objects.requireNonNull(foreignCurrencyAmount);
		Objects.requireNonNull(exchangeRate);

		return Payment.builder()
			.orderId(orderId)
			.currency(currency)
			.foreignCurrencyAmount(foreignCurrencyAmount)
			.exchangeRate(exchangeRate)
			.convertedAmount(foreignCurrencyAmount.multiply(exchangeRate))
			.validUntil(now.plusMinutes(30L))
			.build();
	}

	public boolean isValid(Clock clock) {
		return LocalDateTime.now(clock).isBefore(this.validUntil);
	}
}
