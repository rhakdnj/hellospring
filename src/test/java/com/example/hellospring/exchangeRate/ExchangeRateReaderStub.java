package com.example.hellospring.exchangeRate;

import com.example.hellospring.payment.ExchangeRateReader;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;

@RequiredArgsConstructor
public class ExchangeRateReaderStub implements ExchangeRateReader {
	private final BigDecimal exchangeRate;

	@Override
	public BigDecimal getExchangeRate(String currency) throws IOException {
		return exchangeRate;
	}
}