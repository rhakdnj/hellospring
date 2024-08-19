package com.example.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
	public static void main(String[] args) throws IOException {
		var exchangeRateReader = new WebExchangeRateReader();
		var paymentService = new PaymentService(exchangeRateReader);

		var payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(1000.3));
		System.out.println(payment);
	}
}
