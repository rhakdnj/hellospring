package com.example.hellospring.payment;

import com.example.hellospring.exchangeRate.ExchangeRateReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class PaymentService {
	private final ExchangeRateReader exchangeRateReader;

	public PaymentService(@Qualifier("cachedWebExchangeRateReader")ExchangeRateReader exchangeRateReader) {
		this.exchangeRateReader = exchangeRateReader;
	}

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
