package com.example.hellospring;

import com.example.hellospring.order.Order;
import com.example.hellospring.order.OrderNumber;
import com.example.hellospring.order.OrderServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class OrderClient {
	public static void main(String[] args) {
		var beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
		var orderService = beanFactory.getBean(OrderServiceImpl.class);

		Order order = orderService.createOrder(new OrderNumber("1000"), BigDecimal.ONE);

		System.out.println(order);
	}
}
