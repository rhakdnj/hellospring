package com.example.hellospring.order;

import com.example.hellospring.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final JpaTransactionManager transactionManager;

	public Order createOrder(OrderNumber orderNumber, BigDecimal totalPrice) {
		Order order = new Order(orderNumber.value(), totalPrice);

		return new TransactionTemplate(transactionManager).execute(status -> {
			orderRepository.save(order);
			return order;
		});
	}
}
