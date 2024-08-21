package com.example.hellospring.payment;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PaymentService {
	private final ExchangeRateReader exchangeRateReader;
	private final Clock clock;

	public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
		var exchangeRate = this.exchangeRateReader.getExchangeRate(currency);

		return Payment.createPrepared(
			orderId,
			currency,
			foreignCurrencyAmount,
			exchangeRate,
			LocalDateTime.now(this.clock)
		);
	}
}
