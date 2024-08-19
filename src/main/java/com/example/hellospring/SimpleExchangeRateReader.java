package com.example.hellospring;

import java.math.BigDecimal;

public class SimpleExchangeRateReader implements ExchangeRateReader {
	@Override
	public BigDecimal getExchangeRate(String currency) {
		if (currency.equals("USD")) return BigDecimal.valueOf(1000L);
		throw new IllegalArgumentException("Unsupported currency: " + currency);
	}
}
