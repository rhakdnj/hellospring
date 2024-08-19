package com.example.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExchangeRateReader {
	BigDecimal getExchangeRate(String currency) throws IOException;
}
