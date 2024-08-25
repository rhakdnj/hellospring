package com.example.hellospring;

import com.example.hellospring.order.OrderService;
import com.example.hellospring.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
	@Bean
	public OrderService orderService(JpaTransactionManager transactionManager) {
		return new OrderService(orderRepository(), transactionManager);
	}

	@Bean
	public OrderRepository orderRepository() {
		return new OrderRepository();
	}
}