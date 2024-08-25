package com.example.hellospring;

import com.example.hellospring.order.Order;
import com.example.hellospring.repository.OrderRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {
	public static void main(String[] args) {
		var beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
		var orderRepository = beanFactory.getBean(OrderRepository.class);

		orderRepository.save(new Order("1234", BigDecimal.valueOf(1000.3)));
	}
}
