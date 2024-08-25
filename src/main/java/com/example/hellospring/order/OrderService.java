package com.example.hellospring.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	@Qualifier("jdbcTransactionManager")
	private final PlatformTransactionManager transactionManager;

	public Order createOrder(OrderNumber orderNumber, BigDecimal totalPrice) {
		var order = new Order(orderNumber.value(), totalPrice);

		return new TransactionTemplate(transactionManager).execute(status -> {
			orderRepository.save(order);
			return order;
		});
	}
}
