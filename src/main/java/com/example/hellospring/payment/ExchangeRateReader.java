package com.example.hellospring.payment;

import java.math.BigDecimal;

public interface ExchangeRateReader {
	BigDecimal getExchangeRate(String currency);
}
