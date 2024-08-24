package com.example.hellospring;

import com.example.hellospring.payment.PaymentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
	public static void main(String[] args) {
		var beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
		var paymentService = beanFactory.getBean(PaymentService.class);

		var payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(1000.3));
		System.out.println(payment);
	}
}
