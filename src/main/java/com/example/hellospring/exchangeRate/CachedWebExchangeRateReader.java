package com.example.hellospring.exchangeRate;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;

@RequiredArgsConstructor
public class CachedWebExchangeRateReader implements ExchangeRateReader {
	private final ExchangeRateReader exchangeRateReader;

	@Override
	public BigDecimal getExchangeRate(String currency) throws IOException {
		return this.exchangeRateReader.getExchangeRate(currency);
	}
}
