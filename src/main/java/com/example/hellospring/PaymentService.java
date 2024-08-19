package com.example.hellospring;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PaymentService {
	private final ExchangeRateReader exchangeRateReader;

	public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
		var exchangeRate = this.exchangeRateReader.getExchangeRate(currency);
		var convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
		var validUntil = LocalDateTime.now().plusMinutes(30);

		return Payment.builder()
			.orderId(orderId)
			.currency(currency)
			.foreignCurrencyAmount(foreignCurrencyAmount)
			.exchangeRate(exchangeRate)
			.convertedAmount(convertedAmount)
			.validUntil(validUntil)
			.build();
	}
}
