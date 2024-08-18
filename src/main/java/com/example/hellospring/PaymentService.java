package com.example.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {
	public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
		// 환율 가져오기 (https://open.er-api.com/v6/latest/USD)
		var url = new URL("https://open.er-api.com/v6/latest/" + currency);
		var connection = (HttpURLConnection) url.openConnection();
		var br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		var response = br.lines().collect(Collectors.joining());
		br.close();

		var mapper = new ObjectMapper();
		var exchangeRateData = mapper.readValue(response, ExchangeRateData.class);
		var exchangeRate = exchangeRateData.rates().get("KRW");

		// 금액 계산
		var convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);

		// 유효 시간 계산
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

	public static void main(String[] args) throws IOException {
		var paymentService = new PaymentService();
		var payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(1000.3));
		System.out.println(payment);
	}
}
