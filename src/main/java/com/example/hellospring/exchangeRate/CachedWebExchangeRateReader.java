package com.example.hellospring.exchangeRate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component("cachedWebExchangeRateReader")
@RequiredArgsConstructor
public class CachedWebExchangeRateReader implements ExchangeRateReader {
	@Qualifier("webExchangeRateReader")
	private final ExchangeRateReader exchangeRateReader;

	@Override
	public BigDecimal getExchangeRate(String currency) throws IOException {
		return this.exchangeRateReader.getExchangeRate(currency);
	}
}
